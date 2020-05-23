function fillInfo() {
  loadCatalog();
  //loadVisited();
}

function loadCatalog(){
  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      //let data = this.responseText;
      let data = JSON.parse(this.responseText);
      console.log(data);

      let products = data[0];
      let visited = data[1];

      console.log(products);
      console.log(visited);

      var office = document.getElementById("productListOffice");
      var gaming = document.getElementById("productListGaming");
      var professional = document.getElementById("productListProfessional");
      for (var i = 0; i < products.length; ++i) {
        //console.log(data[i]["product_category"]);
        if (products[i]["product_category"] == "Office") {
          let out = "";
          out += "<div class='grid-item'> ";
          out +=
              '<a href="product.html?Id=' +
              products[i]["product_id"] +
              '" name="' +
              products[i]["product_name"] +
              ' style="color:black;text-decoration:none;"> ' +
              products[i]["product_name"] +
              '<div class="grid-img">' +
              '<img src="./img/' +
              products[i]["product_id"] +
              '/0.jpg" class="photo">' +
              "</div>" +
              "<p>" + products[i]["product_price"] + "</p>" +
              "</a>";
          out += "</div>";

          office.innerHTML += out;
        } else if (products[i]["product_category"] == "Gaming") {
          let out = "";
          out += "<div class='grid-item'> ";
          out +=
              '<a href="product.html?Id=' +
              products[i]["product_id"] +
              '" name="' +
              products[i]["product_name"] +
              ' style="color:black;text-decoration:none;"> ' +
              products[i]["product_name"] +
              '<div class="grid-img">' +
              '<img src="./img/' +
              products[i]["product_id"] +
              '/0.jpg" class="photo">' +
              "</div>" +
              "<p>" + products[i]["product_price"] + "</p>" +
              "</a>";
          out += "</div>";

          gaming.innerHTML += out;
        } else if (products[i]["product_category"] == "Professional") {
          let out = "";
          out += "<div class='grid-item'> ";
          out +=
              '<a href="product.html?Id=' +
              products[i]["product_id"] +
              '" name="' +
              products[i]["product_name"] +
              ' style="color:black;text-decoration:none;"> ' +
              products[i]["product_name"] +
              '<div class="grid-img">' +
              '<img src="./img/' +
              products[i]["product_id"] +
              '/0.jpg" class="photo">' +
              "</div>" +
              "<p>" + products[i]["product_price"] + "</p>" +
              "</a>";
          out += "</div>";

          professional.innerHTML += out;

        }
      }

      var recent = document.getElementById("recentProduct")

      if (visited != null){
        for (var i = 0; i < visited.length; ++i) {
          let out = "";
          out += "<div class='grid-item'> ";
          out +=
              '<a href="product.html?Id=' +
              visited[i]["product_id"] +
              '" name="' +
              visited[i]["product_name"] +
              ' style="color:black;text-decoration:none;"> ' +
              visited[i]["product_name"] +
              '<div class="grid-img">' +
              '<img src="./img/' +
              visited[i]["product_id"] +
              '/0.jpg" class="photo">' +
              "</div>" +
              "<p>" + visited[i]["product_price"] + "</p>" +
              "</a>";
          out += "</div>";

          recent.innerHTML += out;
        }
      }
      else{
        recent.innerHTML="<p> you have not viewed any products recently </p>";
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
      var recent = document.getElementById("recentProduct")

      for (var i = 0; i < data.length; ++i) {
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
            "<p>" + data[i]["product_price"] + "</p>" +
            "</a>";
        out += "</div>";

        recent.innerHTML += out;
      }
    }

  };

  xmlhttp.open("POST", "api/fullCatalog", true);
  console.log("sending");
  xmlhttp.send();
}