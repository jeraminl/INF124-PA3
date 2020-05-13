function fillInfo(){
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
}
