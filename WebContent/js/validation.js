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
  var ship = document.getElementById("shipMeth");
  var shipPrice = ship.value;

  if (shipPrice == 1) {
    document.getElementById("shipPrice").innerHTML = 10;
  } else if (shipPrice == 2) {
    document.getElementById("shipPrice").innerHTML = 5;
  } else {
    document.getElementById("shipPrice").innerHTML = 0;
  }

  var price = parseFloat(document.getElementById("cartPrice").value);

  var ship = parseFloat(document.getElementById("shipPrice").innerHTML);

  var finalPrice = (price + ship).toFixed(2);

  document.getElementById("orderTotPrice").innerHTML = finalPrice;
  document.getElementById("finalPrice").value = finalPrice;
}

function loadCart() {
  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      let data = JSON.parse(this.responseText);
      console.log(data);
      var info = document.getElementById("orderProdID");
      var total = 0;
      let out = "";
      for (var i = 0; i < data.length; ++i) {
        out += '<p> Product Name:' + data[i]["product_name"] + ' Price: $' + data[i]["product_price"] + ' </p>';
        total += data[i]["product_price"];
      }
      info.innerHTML = out;
      document.getElementById("cartPrice").value = total;
      document.getElementById("orderTotPrice").innerHTML = total;
    }
  }
  xmlhttp.open("POST", "api/display-cart", true);
  console.log("sending");
  xmlhttp.send();
}