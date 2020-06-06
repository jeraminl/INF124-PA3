function sameAddress() {
  console.log("same ship address");
  var yourAddress = document.getElementById("address").value;
  var yourCity = document.getElementById("city").value;
  var yourState = document.getElementById("state").value;
  var yourZip = document.getElementById("zip").value;

  document.getElementById("billingAddress").value = yourAddress;
  document.getElementById("billingCity").value = yourCity;
  document.getElementById("billingState").value = yourState;
  document.getElementById("billingZip").value = yourZip;
}

function newShip() {
  console.log("shipping changed");
  var shipPrice = document.getElementById("shipMeth").value;

  var ship = 0;
  if (shipPrice == 1) {
    document.getElementById("shipPrice").innerHTML = 10;
    ship = 10;
  } else if (shipPrice == 2) {
    document.getElementById("shipPrice").innerHTML = 5;
    ship = 5;
  } else {
    document.getElementById("shipPrice").innerHTML = 0;
  }

  var price = parseFloat(document.getElementById("cartPrice").value);

  var shipCart = (parseFloat(price) + parseFloat(ship)).toFixed(2);
  document.getElementById("shipCart").value = shipCart;

  if (document.getElementById("taxCart").value != "") {
    price = parseFloat(document.getElementById("taxCart").value);
  }

  var finalPrice = (parseFloat(price) + parseFloat(ship)).toFixed(2);

  document.getElementById("orderTotPrice").innerHTML = finalPrice;
  document.getElementById("finalPrice").value = finalPrice;
}

function loadCart() {
  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      let data = JSON.parse(this.responseText);
      console.log("data for cart");
      console.log(data);
      var info = document.getElementById("orderProdID");
      var cartHTML = document.getElementById("productCart");
      var total = 0;
      let out = "";
      let cart = "";
      for (var i = 0; i < data.length; ++i) {
        console.log(data[i]);
        out +=
          "<p> Product Name:" +
          data[i]["name"] +
          " Price: $" +
          data[i]["price"] +
          " </p>";
        total += data[i]["price"];
        cart += data[i]["id"] + ", ";
      }
      info.innerHTML = out;
      cartHTML.value = cart;
      document.getElementById("cartPrice").value = total;
      document.getElementById("orderTotPrice").innerHTML = total;
    }
  };
  xmlhttp.open("POST", "api/display-cart", true);
  console.log("sending");
  xmlhttp.send();
}
