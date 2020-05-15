function fillInfo(){
  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function (){
    if (this.readyState == 4 && this.status == 200) {
      var data = JSON.parse(this.responseText);
      console.log(data);

      var office = document.getElementById('productListOffice');
      var gaming = document.getElementById('productListGaming');
      var professional = document.getElementById('productListProfessional');


      for (var i = 0; i < data.length; ++i){
        console.log(data[i]['product_category']);
        if (data[i]['product_category'] == 'Office'){
          console.log("filling office category");
          let out = "";
          out += "<div class='grid-item'> ";
          out +=
              '<a href="product.html?Id='+ data[i]['product_id'] +
              '" name="' + data[i]['product_name'] +
              ' style="color:black;text-decoration:none;"> ' +
              data[i]['product_name'] +
              '<div class="grid-img">' +
              '<img src="./img/' + data[i]['product_id'] + '/0.jpg" class="photo">' +
              '</div>' +
              '</a>';
          out += "</div>";

          office.innerHTML += out;
        }
        else if (data[i]['product_category'] == 'Gaming'){
          console.log("filling Gaming category");
          let out = "";
          out += "<div class='grid-item'> ";
          out +=
              '<a href="product.html?Id='+ data[i]['product_id'] +
              '" name="' + data[i]['product_name'] +
              ' style="color:black;text-decoration:none;"> ' +
              data[i]['product_name'] +
              '<div class="grid-img">' +
              '<img src="./img/' + data[i]['product_id'] + '/0.jpg" class="photo">' +
              '</div>' +
              '</a>';
          out += "</div>";

          gaming.innerHTML += out;
        }

        else if (data[i]['product_category'] == 'Professional'){
          console.log("filling Professional category");
          let out = "";
          out += "<div class='grid-item'> ";
          out +=
              '<a href="product.html?Id='+ data[i]['product_id'] +
              '" name="' + data[i]['product_name'] +
              ' style="color:black;text-decoration:none;"> ' +
              data[i]['product_name'] +
              '<div class="grid-img">' +
              '<img src="./img/' + data[i]['product_id'] + '/0.jpg" class="photo">' +
              '</div>' +
              '</a>';
          out += "</div>";

          professional.innerHTML += out;
        }

      }

    }
  };

  xmlhttp.open("GET", "api/fullCatalog",true);
  console.log("sending");
  xmlhttp.send();

  /*
  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function (){
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById('productListOffice').innerHTML = this.responseText;
    }
  };
  xmlhttp.open("GET", "./php/get_product_office.php",true);
  console.log("sending");
  xmlhttp.send();

  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function (){
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById('productListGaming').innerHTML = this.responseText;
    }
  };
  xmlhttp.open("GET", "./php/get_product_gaming.php",true);
  console.log("sending");
  xmlhttp.send();


  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function (){
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById('productListProfessional').innerHTML = this.responseText;
    }
  };
  xmlhttp.open("GET", "./php/get_product_professional.php",true);
  console.log("sending");
  xmlhttp.send();


   */
}
