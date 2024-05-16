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

function addToWeb(p, ele, returnString) {
	var price = `<strong>` + p.price + `&#8363;</strong>`;
	var chitietSp = '/product-detail/' + p.productDetailId; // Đường dẫn đến trang chi tiết sản phẩm
	var newLi =
		`<li class="sanPham">
            <a href="` + chitietSp + `">
                <img src="` + p.imageUrl + `" alt="" onclick="redirectToProductDetail('` + p.productDetailId + `')">
                <h3>` + p.name + `</h3>
                <div class="price">
                    ` + price + `
                </div>
                <div class="tooltip">
                   
                </div>
            </a>
        </li>`;

	if (returnString) return newLi;
	var products = ele || document.getElementById('products');
	products.innerHTML += newLi;
}

function redirectToProductDetail(productDetailId) {
	window.location.href = "/product-detail/" + productDetailId;
}