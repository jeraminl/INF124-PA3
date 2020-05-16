function fillInfo() {
  loadCatalog();
  loadVisited();
}

function loadCatalog(){
  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      let data = JSON.parse(this.responseText);
      console.log(data);

      var office = document.getElementById("productListOffice");
      var gaming = document.getElementById("productListGaming");
      var professional = document.getElementById("productListProfessional");

      for (var i = 0; i < data.length; ++i) {
        //console.log(data[i]["product_category"]);
        if (data[i]["product_category"] == "Office") {
          let out = "";
          out += "<div class='grid-item'> ";
          out +=
              '<a href="product.html?Id=' +
              data[i]["product_id"] +
              '" name="' +
              data[i]["product_name"] +
              ' style="color:black;text-decoration:none;"> ' +
              data[i]["product_name"] +
              '<div class="grid-img">' +
              '<img src="./img/' +
              data[i]["product_id"] +
              '/0.jpg" class="photo">' +
              "</div>" +
              "<p>" + data[i]["product_price"]  + "</p>" +
              "</a>";
          out += "</div>";

          office.innerHTML += out;
        } else if (data[i]["product_category"] == "Gaming") {
          let out = "";
          out += "<div class='grid-item'> ";
          out +=
              '<a href="product.html?Id=' +
              data[i]["product_id"] +
              '" name="' +
              data[i]["product_name"] +
              ' style="color:black;text-decoration:none;"> ' +
              data[i]["product_name"] +
              '<div class="grid-img">' +
              '<img src="./img/' +
              data[i]["product_id"] +
              '/0.jpg" class="photo">' +
              "</div>" +
              "<p>" + data[i]["product_price"]  + "</p>" +
              "</a>";
          out += "</div>";

          gaming.innerHTML += out;
        } else if (data[i]["product_category"] == "Professional") {
          let out = "";
          out += "<div class='grid-item'> ";
          out +=
              '<a href="product.html?Id=' +
              data[i]["product_id"] +
              '" name="' +
              data[i]["product_name"] +
              ' style="color:black;text-decoration:none;"> ' +
              data[i]["product_name"] +
              '<div class="grid-img">' +
              '<img src="./img/' +
              data[i]["product_id"] +
              '/0.jpg" class="photo">' +
              "</div>" +
              "<p>" + data[i]["product_price"]  + "</p>" +
              "</a>";
          out += "</div>";

          professional.innerHTML += out;
        }
      }
    }
  };

  xmlhttp.open("GET", "api/fullCatalog", true);
  console.log("sending");
  xmlhttp.send();
}

function loadVisited(){
  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      data = this.responseText;
      console.log("items visited: " + data);
    }

  };

  xmlhttp.open("POST", "api/fullCatalog", true);
  console.log("sending");
  xmlhttp.send();
}