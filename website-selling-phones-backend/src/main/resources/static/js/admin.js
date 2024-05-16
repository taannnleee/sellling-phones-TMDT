var TONGTIEN = 0;

window.onload = function () {
    // get data từ localstorage
    list_products = getListProducts() || list_products;
    adminInfo = getListAdmin() || adminInfo;

    addEventChangeTab();

    if (window.localStorage.getItem('admin')) {
        addTableProducts();
        addTableDonHang();
        addTableKhachHang();
        addThongKe();

        openTab('Trang Chủ')
    } else {
        addTableProducts();
        addTableDonHang();
        addTableKhachHang();
        addThongKe();

        openTab('Trang Chủ')
    }
}

function logOutAdmin() {
    window.localStorage.removeItem('admin');
}

function getListRandomColor(length) {
    let result = [];
    for(let i = length; i--;) {
        result.push(getRandomColor());
    }
    return result;
}

function addChart(id, chartOption) {
    var ctx = document.getElementById(id).getContext('2d');
    var chart = new Chart(ctx, chartOption);
}

function createChartConfig(
    title = 'Title',
    charType = 'bar',
    labels = ['nothing'],
    data = [2],
    colors = ['red'],
) {
    return {
        type: charType,
        data: {
            labels: labels,
            datasets: [{
                label: title,
                data: data,
                backgroundColor: colors,
                borderColor: colors,
                // borderWidth: 2
            }]
        },
        options: {
            title: {
                fontColor: '#fff',
                fontSize: 25,
                display: true,
                text: title
            },
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero:true
                    }
                }]
            }
        }
    };
}

function addThongKe() {
    var danhSachDonHang = getListDonHang(true);

    var thongKeHang = {}; // Thống kê hãng

    danhSachDonHang.forEach(donHang => {
        // Nếu đơn hàng bị huỷ thì không tính vào số lượng bán ra
        if(donHang.tinhTrang === 'Đã hủy') return;

        // Lặp qua từng sản phẩm trong đơn hàng
        donHang.sp.forEach(sanPhamTrongDonHang => {
            let tenHang = sanPhamTrongDonHang.sanPham.company;
            let soLuong = sanPhamTrongDonHang.soLuong;
            let donGia = stringToNum(sanPhamTrongDonHang.sanPham.price);
            let thanhTien = soLuong * donGia;

            if(!thongKeHang[tenHang]) {
                thongKeHang[tenHang] = {
                    soLuongBanRa: 0,
                    doanhThu: 0,
                }
            }

            thongKeHang[tenHang].soLuongBanRa += soLuong;
            thongKeHang[tenHang].doanhThu += thanhTien;
        })
    })


    // Lấy mảng màu ngẫu nhiên để vẽ đồ thị
    let colors = getListRandomColor(Object.keys(thongKeHang).length);

    // Thêm thống kê
    addChart('myChart1', createChartConfig(
        'Số lượng bán ra',
        'bar',
        Object.keys(thongKeHang),
        Object.values(thongKeHang).map(_ =>  _.soLuongBanRa),
        colors,
    ));

    addChart('myChart2', createChartConfig(
        'Doanh thu',
        'doughnut',
        Object.keys(thongKeHang),
        Object.values(thongKeHang).map(_ =>  _.doanhThu),
        colors,
    ));

    // var doughnutChart = copyObject(dataChart);
    //     doughnutChart.type = 'doughnut';
    // addChart('myChart2', doughnutChart);

    // var pieChart = copyObject(dataChart);
    //     pieChart.type = 'pie';
    // addChart('myChart3', pieChart);

    // var lineChart = copyObject(dataChart);
    //     lineChart.type = 'line';
    // addChart('myChart4', lineChart);
}

// ======================= Các Tab =========================
function addEventChangeTab() {
    var sidebar = document.getElementsByClassName('sidebar')[0];
    var list_a = sidebar.getElementsByTagName('a');
    for(var a of list_a) {
        if(!a.onclick) {
            a.addEventListener('click', function() {
                turnOff_Active();
                this.classList.add('active');
                var tab = this.childNodes[1].data.trim()
                openTab(tab);
            })
        }
    }
}

function turnOff_Active() {
    var sidebar = document.getElementsByClassName('sidebar')[0];
    var list_a = sidebar.getElementsByTagName('a');
    for(var a of list_a) {
        a.classList.remove('active');
    }
}

function openTab(nameTab) {
    // ẩn hết
    var main = document.getElementsByClassName('main')[0].children;
    for(var e of main) {
        e.style.display = 'none';
    }

    // mở tab
    switch(nameTab) {
        case 'Trang Chủ': document.getElementsByClassName('home')[0].style.display = 'block'; break;
        case 'Sản Phẩm': document.getElementsByClassName('sanpham')[0].style.display = 'block'; break;
        case 'Đơn Hàng': document.getElementsByClassName('donhang')[0].style.display = 'block'; break;
        case 'Khách Hàng': document.getElementsByClassName('khachhang')[0].style.display = 'block'; break;
    }
}
// ========================== Sản Phẩm ========================
// Vẽ bảng danh sách sản phẩm
async function fetchProductDetails() {
    try {
        const response = await fetch('/api/productDetails');
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const productDetails = await response.json();
        console.log(productDetails);
        return productDetails;
    } catch (error) {
        console.error('Could not fetch product details:', error);
    }
}

// Hàm cập nhật bảng sản phẩm
async function addTableProducts() {
    const productDetails = await fetchProductDetails();
    if (!productDetails) return;

    let s = `<table class="table-outline hideImg">`;
    for (var i = 0; i < productDetails.length; i++) {
        var p = productDetails[i];
        s += `<tr>
            <td style="width: 5%">${i + 1}</td>
            <td style="width: 10%">${p.productDetailId}</td>
            <td style="width: 40%">
                <a title="Xem chi tiết" target="_blank" href="chitietsanpham.html?${p.name.split(' ').join('-')}">${p.name}</a>
                <img src="${p.localImagePath}" alt="${p.name}"></img>
            </td>
            <td style="width: 15%">${p.price}</td>
            <td style="width: 15%">${p.color}</td>
            <td style="width: 15%">
                 <div class="tooltip">
                     <i class="fa fa-wrench" onclick="addKhungSuaSanPham('${p.productDetailId}')"></i>
                     <span class="tooltiptext">Sửa</span>
                 </div>
                 <div class="tooltip">
                     <i class="fa fa-trash" onclick="xoaSanPham('${p.productDetailId}', '${p.name}')"></i>
                     <span class="tooltiptext">Xóa</span>
                 </div>
            </td>
        </tr>`;
    }
    s += `</table>`;
    document.getElementsByClassName('sanpham')[0].getElementsByClassName('table-content')[0].innerHTML = s;
}

// Them san pham
function themSanPham() {
    var xhr = new XMLHttpRequest();

    var productDetailDTO = {
        name: document.getElementById('name').value,
        category: document.getElementById('category').value,
        imageUrl: document.getElementById('imageUrl').value,
        description: document.getElementById('description').value,
        price: parseFloat(document.getElementById('price').value),
        screen: document.getElementById('screen').value,
        os: document.getElementById('os').value,
        camara: document.getElementById('camara').value,
        camaraFront: document.getElementById('camaraFront').value,
        cpu: document.getElementById('cpu').value,
        ram: document.getElementById('ram').value,
        rom: document.getElementById('rom').value,
        microUSB: document.getElementById('microUSB').value,
        battery: document.getElementById('battery').value,
        color: document.getElementById('color').value
    };

    var data = JSON.stringify(productDetailDTO);

    xhr.open('POST', '/adminProduct/add-product', true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.onload = function () {
        if (xhr.status == 200) {
            alert('Thêm sản phẩm thành công!');
            location.reload();
        } else {
            alert('Có lỗi xảy ra khi thêm sản phẩm. Vui lòng thử lại.');
        }
    };
    xhr.send(data);
}
function xoaSanPham(dataset) {
    var masp = dataset.productId;
    var name = dataset.productName;

    var confirmDelete = confirm('Bạn có chắc chắn muốn xóa sản phẩm ' + name + '?');
    if (!confirmDelete) {
        return;
    }

    var xhr = new XMLHttpRequest();

    xhr.open('DELETE', '/adminProduct/delete-product/' + masp, true);

    xhr.onload = function () {
        if (xhr.status == 200) {
            alert('Xóa sản phẩm thành công!');
            location.reload();
        } else {
            alert('Có lỗi xảy ra khi xóa sản phẩm. Vui lòng thử lại.');
        }
    };
    xhr.send();
}


function addKhungSuaSanPham(element) {
    var dataset = element.dataset;

    document.getElementById('maspSua').value = dataset.productId;
    document.getElementById('nameSua').value = dataset.productName;
    document.getElementById('categorySua').value = dataset.productCategory;
    document.getElementById('anhDaiDienSanPhamSua').src = dataset.productImageUrl;
    document.getElementById('imageUrlSua').src = dataset.productImageUrl;
    document.getElementById('descriptionSua').value = dataset.productDescription;
    document.getElementById('priceSua').value = dataset.productPrice;
    document.getElementById('colorSua').value = dataset.productColor;
    document.getElementById('screenSua').value = dataset.productScreen;
    document.getElementById('osSua').value = dataset.productOs;
    document.getElementById('camaraSua').value = dataset.productCamara;
    document.getElementById('camaraFrontSua').value = dataset.productCamaraFront;
    document.getElementById('cpuSua').value = dataset.productCpu;
    document.getElementById('ramSua').value = dataset.productRam;
    document.getElementById('romSua').value = dataset.productRom;
    document.getElementById('microUSBSua').value = dataset.productMicroUsb;
    document.getElementById('batterySua').value = dataset.productBattery;

    document.getElementById('khungSuaSanPham').style.transform = 'scale(1)';
}
function suaSanPham() {
    var xhr = new XMLHttpRequest();

    var productDetailDTO = {
        id: document.getElementById('maspSua').value,
        name: document.getElementById('nameSua').value,
        category: document.getElementById('categorySua').value,
        imageUrl: document.getElementById('imageUrl').value,
        description: document.getElementById('descriptionSua').value,
        price: parseFloat(document.getElementById('priceSua').value),
        screen: document.getElementById('screenSua').value,
        os: document.getElementById('osSua').value,
        camara: document.getElementById('camaraSua').value,
        camaraFront: document.getElementById('camaraFrontSua').value,
        cpu: document.getElementById('cpuSua').value,
        ram: document.getElementById('ramSua').value,
        rom: document.getElementById('romSua').value,
        microUSB: document.getElementById('microUSBSua').value,
        battery: document.getElementById('batterySua').value,
        color: document.getElementById('colorSua').value
    };

    var data = JSON.stringify(productDetailDTO);

    xhr.open('PUT', '/adminProduct/update-product/' + productDetailDTO.id, true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.onload = function () {
        if (xhr.status == 200) {
            alert('Cập nhật sản phẩm thành công!');
            location.reload();
        } else {
            alert('Có lỗi xảy ra khi cập nhật sản phẩm. Vui lòng thử lại.');
        }
    };
    xhr.send(data);
}
function capNhatAnhSanPham(files, imgId) {
    var fileName = files[0].name;
    var newFilePath = 'img/products/' + fileName;
    document.getElementById(imgId).src = newFilePath;
    document.getElementById('imageUrl').value = newFilePath;
}
function timKiemSanPham(inp) {
    var kieuTim = document.getElementsByName('kieuTimSanPham')[0].value.toLowerCase();
    var text = inp.value.trim().toLowerCase();

    var tableRows = document.querySelectorAll('.table-outline.hideImg tr');

    tableRows.forEach(function(row) {
        var cell = row.cells[kieuTim];
        if (cell) {
            var cellText = cell.innerText.toLowerCase();
            if (text === '' || cellText.includes(text)) {
                row.style.display = '';
            } else {
                row.style.display = 'none';
            }
        }
    });
}
function sortProductsTable(loai) {
    var table = document.getElementById('uniqueTable');
    var rows = table.getElementsByTagName('tr');
    var switching = true;
    var shouldSwitch = false;

    while (switching) {
        switching = false;

        for (var i = 1; i < (rows.length - 1); i++) {
            var x = rows[i].getElementsByTagName('td')[getValueIndex(loai)].textContent.toLowerCase();
            var y = rows[i + 1].getElementsByTagName('td')[getValueIndex(loai)].textContent.toLowerCase();

            if ((loai !== 'stt' && x > y) || (loai === 'stt' && parseInt(x) > parseInt(y))) {
                shouldSwitch = true;
                break;
            }
        }

        if (shouldSwitch) {
            if (rows[i] && rows[i + 1]) {
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
            } else {
                console.error('Invalid rows:', rows[i], rows[i + 1]);
            }
        }
    }
}

function getValueIndex(type) {
    switch (type) {
        case 'stt':
            return 0;
        case 'masp':
            return 1;
        case 'ten':
            return 2;
        case 'gia':
            return 3;
        case 'mau':
            return 4;
        default:
            return -1;
    }
}
// ========================= Đơn Hàng ===========================
// Vẽ bảng
function addTableDonHang() {
    var tc = document.getElementsByClassName('donhang')[0].getElementsByClassName('table-content')[0];
    var s = `<table class="table-outline hideImg">`;

    var listDH = getListDonHang();

    TONGTIEN = 0;
    for (var i = 0; i < listDH.length; i++) {
        var d = listDH[i];
        s += `<tr>
            <td style="width: 5%">` + (i+1) + `</td>
            <td style="width: 13%">` + d.ma + `</td>
            <td style="width: 7%">` + d.khach + `</td>
            <td style="width: 20%">` + d.sp + `</td>
            <td style="width: 15%">` + d.tongtien + `</td>
            <td style="width: 10%">` + d.ngaygio + `</td>
            <td style="width: 10%">` + d.tinhTrang + `</td>
            <td style="width: 10%">
                <div class="tooltip">
                    <i class="fa fa-check" onclick="duyet('`+d.ma+`', true)"></i>
                    <span class="tooltiptext">Duyệt</span>
                </div>
                <div class="tooltip">
                    <i class="fa fa-remove" onclick="duyet('`+d.ma+`', false)"></i>
                    <span class="tooltiptext">Hủy</span>
                </div>

            </td>
        </tr>`;
        TONGTIEN += stringToNum(d.tongtien);
    }

    s += `</table>`;
    tc.innerHTML = s;
}

function getListDonHang(traVeDanhSachSanPham = false) {
    var u = getListUser();
    var result = [];
    for(var i = 0; i < u.length; i++) {
        for(var j = 0; j < u[i].donhang.length; j++) {
            // Tổng tiền
            var tongtien = 0;
            for(var s of u[i].donhang[j].sp) {
                var timsp = timKiemTheoMa(list_products, s.ma);
                if(timsp.promo.name == 'giareonline') tongtien += stringToNum(timsp.promo.value);
                else tongtien += stringToNum(timsp.price);
            }

            // Ngày giờ
            var x = new Date(u[i].donhang[j].ngaymua).toLocaleString();

            // Các sản phẩm - dạng html
            var sps = '';
            for(var s of u[i].donhang[j].sp) {
                sps += `<p style="text-align: right">`+(timKiemTheoMa(list_products, s.ma).name + ' [' + s.soluong + ']') + `</p>`;
            }

            // Các sản phẩm - dạng mảng
            var danhSachSanPham = [];
            for(var s of u[i].donhang[j].sp) {
                danhSachSanPham.push({
                    sanPham: timKiemTheoMa(list_products, s.ma),
                    soLuong: s.soluong,
                });
            }

            // Lưu vào result
            result.push({
                "ma": u[i].donhang[j].ngaymua.toString(),
                "khach": u[i].username,
                "sp": traVeDanhSachSanPham ? danhSachSanPham : sps,
                "tongtien": numToString(tongtien),
                "ngaygio": x,
                "tinhTrang": u[i].donhang[j].tinhTrang
            });
        }
    }
    return result;
}

// Duyệt
function duyet(maDonHang, duyetDon) {
    var u = getListUser();
    for(var i = 0; i < u.length; i++) {
        for(var j = 0; j < u[i].donhang.length; j++) {
            if(u[i].donhang[j].ngaymua == maDonHang) {
                if(duyetDon) {
                    if(u[i].donhang[j].tinhTrang == 'Đang chờ xử lý') {
                        u[i].donhang[j].tinhTrang = 'Đã giao hàng';

                    } else if(u[i].donhang[j].tinhTrang == 'Đã hủy') {
                        alert('Không thể duyệt đơn đã hủy !');
                        return;
                    }
                } else {
                    if(u[i].donhang[j].tinhTrang == 'Đang chờ xử lý') {
                        if(window.confirm('Bạn có chắc muốn hủy đơn hàng này. Hành động này sẽ không thể khôi phục lại !'))
                            u[i].donhang[j].tinhTrang = 'Đã hủy';

                    } else if(u[i].donhang[j].tinhTrang == 'Đã giao hàng') {
                        alert('Không thể hủy đơn hàng đã giao !');
                        return;
                    }
                }
                break;
            }
        }
    }

    // lưu lại
    setListUser(u);

    // vẽ lại
    addTableDonHang();
}

function locDonHangTheoKhoangNgay() {
    var from = document.getElementById('fromDate').valueAsDate;
    var to = document.getElementById('toDate').valueAsDate;

    var listTr_table = document.getElementsByClassName('donhang')[0].getElementsByClassName('table-content')[0].getElementsByTagName('tr');
    for (var tr of listTr_table) {
        var td = tr.getElementsByTagName('td')[5].innerHTML;
        var d = new Date(td);

        if (d >= from && d <= to) {
            tr.style.display = '';
        } else {
            tr.style.display = 'none';
        }
    }
}

function timKiemDonHang(inp) {
    var kieuTim = document.getElementsByName('kieuTimDonHang')[0].value;
    var text = inp.value;

    // Lọc
    var vitriKieuTim = {'ma':1, 'khachhang':2, 'trangThai':6};

    var listTr_table = document.getElementsByClassName('donhang')[0].getElementsByClassName('table-content')[0].getElementsByTagName('tr');
    for (var tr of listTr_table) {
        var td = tr.getElementsByTagName('td')[vitriKieuTim[kieuTim]].innerHTML.toLowerCase();

        if (td.indexOf(text.toLowerCase()) < 0) {
            tr.style.display = 'none';
        } else {
            tr.style.display = '';
        }
    }
}

// Sắp xếp
function sortDonHangTable(loai) {
    var list = document.getElementsByClassName('donhang')[0].getElementsByClassName("table-content")[0];
    var tr = list.getElementsByTagName('tr');

    quickSort(tr, 0, tr.length-1, loai, getValueOfTypeInTable_DonHang);
    decrease = !decrease;
}

// Lấy giá trị của loại(cột) dữ liệu nào đó trong bảng
function getValueOfTypeInTable_DonHang(tr, loai) {
    var td = tr.getElementsByTagName('td');
    switch(loai) {
        case 'stt': return Number(td[0].innerHTML);
        case 'ma' : return new Date(td[1].innerHTML); // chuyển về dạng ngày để so sánh ngày
        case 'khach' : return td[2].innerHTML.toLowerCase(); // lấy tên khách
        case 'sanpham' : return td[3].children.length;    // lấy số lượng hàng trong đơn này, length ở đây là số lượng <p>
        case 'tongtien' : return stringToNum(td[4].innerHTML); // trả về dạng giá tiền
        case 'ngaygio' : return new Date(td[5].innerHTML); // chuyển về ngày
        case 'trangthai': return td[6].innerHTML.toLowerCase(); //
    }
    return false;
}

// ====================== Khách Hàng =============================

function timKiemNguoiDung(inp) {
    var kieuTim = document.getElementsByName('kieuTimKhachHang')[0].value;
    var text = inp.value.toLowerCase();

    // Lọc
    var vitriKieuTim = {'ten':1, 'email':2, 'sodt':3};

    var listTr_table = document.getElementById('uniqueTable').getElementsByTagName('tr');
    for (var i = 1; i < listTr_table.length; i++) {
        var tr = listTr_table[i];
        var td = tr.getElementsByTagName('td')[vitriKieuTim[kieuTim]];
        if (td) {
            var tdText = td.textContent || td.innerText;
            if (tdText.toLowerCase().indexOf(text) > -1) {
                tr.style.display = "";
            } else {
                tr.style.display = "none";
            }
        }
    }
}

function openThemNguoiDung() {
    window.alert('Not Available!');
}
function themKhachHang() {
    var xhr = new XMLHttpRequest();

    var customerDTO = {
        firstName: document.getElementById('firstName').value,
        lastName: document.getElementById('lastName').value,
        email: document.getElementById('email').value,
        phoneNumber: document.getElementById('phoneNumber').value,
        passHash: document.getElementById('password').value
    };

    var data = JSON.stringify(customerDTO);

    xhr.open('POST', '/admin/add-customer', true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.onload = function () {
            alert('Thêm khách hàng thành công!');
            location.reload();
    };
    xhr.send(data);
}


function xoaKhachHang(dataset) {
    var customerId = dataset.customerId;
    var name = dataset.customerName;

    var confirmDelete = confirm('Bạn có chắc chắn muốn xóa khách hàng ' + name + '?');
    if (!confirmDelete) {
        return;
    }

    var xhr = new XMLHttpRequest();

    xhr.open('DELETE', '/admin/delete-customer/' + customerId, true);

    xhr.onload = function () {
        alert('Xóa khách hàng thành công!');
            location.reload();
    };
    xhr.send();
}
function addKhungSuaKhachHang(element) {
    var dataset = element.dataset;
    console.log(dataset.firstName);

    document.getElementById('customerIdSua').value = dataset.customerId;
    document.getElementById('firstNameSua').value = dataset.firstName;
    document.getElementById('lastNameSua').value = dataset.lastName;
    document.getElementById('emailSua').value = dataset.email;
    document.getElementById('phoneNumberSua').value = dataset.phoneNumber;
    document.getElementById('passwordSua').value = dataset.passHash;

    document.getElementById('khungSuaKhachHang').style.transform = 'scale(1)';
}

function suaKhachHang() {
    var xhr = new XMLHttpRequest();

    var customerDTO = {
        customerId: document.getElementById('customerIdSua').value,
        firstName: document.getElementById('firstNameSua').value,
        lastName: document.getElementById('lastNameSua').value,
        email: document.getElementById('emailSua').value,
        phoneNumber: document.getElementById('phoneNumberSua').value,
        passHash: document.getElementById('passwordSua').value
    };

    var data = JSON.stringify(customerDTO);

    xhr.open('PUT', '/admin/update-customer/' + customerDTO.customerId, true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.onload = function () {
        if (xhr.status == 200) {
            alert('Cập nhật khách hàng thành công!');
            location.reload();
        } else {
            alert('Có lỗi xảy ra khi cập nhật khách hàng. Vui lòng thử lại.');
        }
    };
    xhr.send(data);
}

// Khach hang
async function fetchCustomerDetails() {
    try {
        const response = await fetch('/admin');
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const customerDetails = await response.json();
        return customerDetails;
    } catch (error) {
        console.error('Could not fetch customer details:', error);
    }
}
async function addTableKhachHang() {
    const customerDetails = await fetchCustomerDetails();
    if (!customerDetails) return;

    let s = `<table class="table-outline hideImg">`;
    for (var i = 0; i < customerDetails.length; i++) {
        var c = customerDetails[i];
        s += `<tr>
            <td style="width: 5%">${i + 1}</td>
            <td style="width: 15%">${c.firstName} ${c.lastName}</td>
            <td style="width: 20%">${c.email}</td>
            <td style="width: 20%">${c.phoneNumber}</td>
            <td style="width: 10%">${c.address}</td>
            <td style="width: 10%">
                <div class="tooltip">
                    <i class="fa fa-remove" onclick="xoaKhachHang('${c.customerId}', '${c.firstName} ${c.lastName}')"></i>
                    <span class="tooltiptext">Xóa</span>
                </div>
            </td>
        </tr>`;
    }
    s += `</table>`;
    document.getElementsByClassName('khachhang')[0].getElementsByClassName('table-content')[0].innerHTML = s;
}

// Sắp xếp
var sortStatus = {};

function sortKhachHangTable(loai) {
    var table = document.getElementById('uniqueTable');
    var rows = table.getElementsByTagName('tr');
    var switching = true;
    var shouldSwitch = false;
    var direction = sortStatus[loai] || 'asc';

    while (switching) {
        switching = false;

        for (var i = 1; i < (rows.length - 1); i++) {
            var x = rows[i].getElementsByTagName('td')[getKhachHangValueIndex(loai)].textContent.toLowerCase();
            var y = rows[i + 1].getElementsByTagName('td')[getKhachHangValueIndex(loai)].textContent.toLowerCase();

            if (direction === 'asc' && ((loai !== 'stt' && x > y) || (loai === 'stt' && parseInt(x) > parseInt(y)))) {
                shouldSwitch = true;
                break;
            } else if (direction === 'desc' && ((loai !== 'stt' && x < y) || (loai === 'stt' && parseInt(x) < parseInt(y)))) {
                shouldSwitch = true;
                break;
            }
        }

        if (shouldSwitch) {
            if (rows[i] && rows[i + 1]) {
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
            } else {
                console.error('Invalid rows:', rows[i], rows[i + 1]);
            }
        } else if (direction === 'asc') {
            sortStatus[loai] = 'desc';
        } else {
            sortStatus[loai] = 'asc';
        }
    }
}


function getKhachHangValueIndex(type) {
    switch (type) {
        case 'stt':
            return 0;
        case 'hoten':
            return 1;
        case 'email':
            return 2;
        case 'sodt':
            return 3;
        default:
            return -1;
    }
}


function getValueOfTypeInTable_KhachHang(tr, loai) {
    var td = tr.getElementsByTagName('td');
    switch(loai) {
        case 'stt': return Number(td[0].innerHTML);
        case 'hoten' : return td[1].innerHTML.toLowerCase();
        case 'email' : return td[2].innerHTML.toLowerCase();
        case 'sodt' : return td[3].innerHTML.toLowerCase();
        case 'diachi' : return td[4].innerHTML.toLowerCase();
    }
    return false;
}

// ================== Sort ====================
// https://github.com/HoangTran0410/First_html_css_js/blob/master/sketch.js
var decrease = true; // Sắp xếp giảm dần

// loại là tên cột, func là hàm giúp lấy giá trị từ cột loai
function quickSort(arr, left, right, loai, func) {
    var pivot,
        partitionIndex;

    if (left < right) {
        pivot = right;
        partitionIndex = partition(arr, pivot, left, right, loai, func);

        //sort left and right
        quickSort(arr, left, partitionIndex - 1, loai, func);
        quickSort(arr, partitionIndex + 1, right, loai, func);
    }
    return arr;
}

function partition(arr, pivot, left, right, loai, func) {
    var pivotValue =  func(arr[pivot], loai),
        partitionIndex = left;

    for (var i = left; i < right; i++) {
        if (decrease && func(arr[i], loai) > pivotValue
        || !decrease && func(arr[i], loai) < pivotValue) {
            swap(arr, i, partitionIndex);
            partitionIndex++;
        }
    }
    swap(arr, right, partitionIndex);
    return partitionIndex;
}

function swap(arr, i, j) {
    var tempi = arr[i].cloneNode(true);
    var tempj = arr[j].cloneNode(true);
    arr[i].parentNode.replaceChild(tempj, arr[i]);
    arr[j].parentNode.replaceChild(tempi, arr[j]);
}

// ================= các hàm thêm ====================
// Chuyển khuyến mãi vễ dạng chuỗi tiếng việt
function promoToStringValue(pr) {
    switch (pr.name) {
        case 'tragop':
            return 'Góp ' + pr.value + '%';
        case 'giamgia':
            return 'Giảm ' + pr.value;
        case 'giareonline':
            return 'Online (' + pr.value + ')';
        case 'moiramat':
            return 'Mới';
    }
    return '';
}

function progress(percent, bg, width, height) {

    return `<div class="progress" style="width: ` + width + `; height:` + height + `">
                <div class="progress-bar bg-info" style="width: ` + percent + `%; background-color:` + bg + `"></div>
            </div>`
}

// for(var i = 0; i < list_products.length; i++) {
//     list_products[i].masp = list_products[i].company.substring(0, 3) + vitriCompany(list_products[i], i);
// }

// console.log(JSON.stringify(list_products));