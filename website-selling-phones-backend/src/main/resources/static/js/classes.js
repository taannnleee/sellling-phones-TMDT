function User(username, pass, ho, ten, email, products, donhang) {
	this.ho = ho || '';
	this.ten = ten || '';
	this.email = email || '';

	this.username = username;
	this.pass = pass;
	this.products = products || [];
	this.donhang = donhang || [];
}

function equalUser(u1, u2) {
	return (u1.username == u2.username && u1.pass == u2.pass);
}

function Promo(name, value) { // khuyen mai
	this.name = name; // giamGia, traGop, giaReOnline
	this.value = value;

	this.toWeb = function () {
		if (!this.name) return "";
		var contentLabel = "";
		switch (this.name) {
			case "giamgia":
				contentLabel = `<i class="fa fa-bolt"></i> Giảm ` + this.value + `&#8363;`;
				break;

			case "tragop":
				contentLabel = `Trả góp ` + this.value + `%`;
				break;

			case "giareonline":
				contentLabel = `Giá rẻ online`;
				break;

			case "moiramat":
				contentLabel = "Mới ra mắt";
				break;
		}

		var label =
			`<label class=` + this.name + `>
			` + contentLabel + `
		</label>`;

		return label;
	}
}

function Product(productDetailId, name, imageUrl, price) {
	this.productDetailId = productDetailId;
	this.imageUrl = imageUrl;
	this.name = name;
	this.price = price;
}

// img th:src="@{/img/{image}(image=${category.urlImage})}" alt="NotFound"

function formatPrice(price) {
	// Chuyển đổi giá thành chuỗi
	var priceStr = price.toFixed(0).toString();

	// Tạo biến để lưu giá đã định dạng
	var formattedPrice = '';

	// Duyệt qua từng ký tự trong chuỗi giá
	for (var i = priceStr.length - 1, count = 0; i >= 0; i--, count++) {
		// Nếu đã đến mỗi 3 chữ số, thêm dấu chấm vào chuỗi
		if (count !== 0 && count % 3 === 0) {
			formattedPrice = '.' + formattedPrice;
		}
		// Thêm ký tự vào chuỗi đã định dạng
		formattedPrice = priceStr[i] + formattedPrice;
	}

	// Thêm đơn vị tiền tệ
	formattedPrice += ' VND';

	return formattedPrice;
}
function addToWeb(p, ele, returnString) {
	var price = `<strong>` + formatPrice(p.price) + `</strong>`;
	var chitietSp = '/product-detail/' + p.productDetailId; // Đường dẫn đến trang chi tiết sản phẩm
	var productID = getProductIdByProductDetailId(p.productDetailId)
	var newLi =
		`<li class="sanPham">
            <a href="` + chitietSp + `">
                <img src="img/${p.imageUrl}" alt="" >

                <h3>` + p.name + `</h3>
                <div class="price">
                    ` + price + `
                </div>
                
            </a>
            <div>
				<button class="muangay" onClick="redirectToCart('${p.productDetailId}')">
						<i class="fas fa-shopping-cart"></i>
				</button>
			</div>
        </li>`;

	if (returnString) return newLi;
	var products = ele || document.getElementById('products');
	products.innerHTML += newLi;
}

function redirectToProductDetail(productDetailId) {
	window.location.href = "/product-detail/" + productDetailId;
}

// Thêm một hàm mới để xử lý việc chuyển hướng
function redirectToCart(productDetailId) {
	var productId = getProductIdByProductDetailId(productDetailId);
	if (productId) {
		window.location.href = "/cart/add-to-cart/" + productId;
	} else {
		// Xử lý trường hợp productId không tồn tại
		console.error("Product ID not found for product detail ID:", productDetailId);
	}
}

function getProductIdByProductDetailId(productDetailId) {
	if (productIDMap && productIDMap.hasOwnProperty(productDetailId)) {
		return productIDMap[productDetailId]; // trả về productID
	} else {
		return null;
	}
}
