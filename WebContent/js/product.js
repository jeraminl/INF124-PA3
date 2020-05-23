function fillInfo() {
  var params = new URLSearchParams(window.location.search);
  var productID = params.get("Id");
  console.log(productID);

  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      let data = JSON.parse(this.responseText);
      console.log(data);
      var info = document.getElementById("productInfo");

      for (var i = 0; i < data.length; ++i) {
        let out = "";

        out += "<a href='index.html'></a>";
        out += "<h1 id='productName' class='prod-title'></h1>";
        out += "<div id='image-row' class='image-row'>";
        out += "<div id='imgModal' class='modal'>";
        out += "<span class='close'>x</span>";
        out += "<img class='modal-content' id='img' />";
        out += "</div>";
        out +=
          '<div class="image-column"><img src="img/' +
          productID +
          '/0.jpg" class="photo" id="img0" onload="modalZoom(this)"></div>';
        out +=
          '<div class="image-column"><img src="img/' +
          productID +
          '/1.jpg" class="photo" id="img1" onload="modalZoom(this)"></div>';
        out +=
          '<div class="image-column"><img src="img/' +
          productID +
          '/2.jpg" class="photo" id="img2" onload="modalZoom(this)"></div>';

        out += "</div>";

        out +=
          '<p> Product ID:<span id="productID">' +
          data[i]["product_id"] +
          " </span></p>";
        out +=
          '<p id="desc" class="prod-details">Descriptions: ' +
          data[i]["product_description"] +
          "</p>";
        out +=
          '<p id="price" class="price"> $' + data[i]["product_price"] + " </p>";
        out += "<p>Additional Details</p>";
        out += "<ul>";
        out +=
          '<li id="size" class="list-items">Size: ' +
          data[i]["product_size"] +
          "</li>";
        out +=
          '<li id="key" class="list-items">Keys Switch:' +
          data[i]["product_switch"] +
          "</li>";
        out += "</ul>";

        info.innerHTML = out;
      }
    }
  };
  xmlhttp.open("GET", "api/single-product?id=" + productID, true);
  console.log("sending");
  xmlhttp.send();
}

function modalZoom(el) {
  var modal = document.getElementById("imgModal");

  var modalImg = document.getElementById("img");
  el.onclick = function() {
    modal.style.display = "block";
    modalImg.src = el.src;
  };

  var span = document.getElementsByClassName("close")[0];
  span.onclick = function() {
    modal.style.display = "none";
  };
}

function addToCart() {
  var params = new URLSearchParams(window.location.search);
  var productID = params.get("Id");

  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      console.log("added item to cart");
      alert("item added to cart");
      data = this.responseText;

      console.log(data);
    }
  };

  xmlhttp.open("POST", "api/session?id=" + productID, true);
  console.log("sending");
  xmlhttp.send();
}
