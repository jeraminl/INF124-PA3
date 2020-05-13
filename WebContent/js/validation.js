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

  var price = parseFloat(document.getElementById("orderPrice").innerHTML);
  var oldUnits = document.getElementById("units");
  var newUnits = parseFloat(oldUnits.value);
  var ship = parseFloat(document.getElementById("shipPrice").innerHTML);

  var tax = document.getElementById("taxPrice");
  var newTax = parseFloat(tax.innerHTML);

  if (newTax == "") {
    newTax = 0;
  }
  var finalPrice = (
    price * newUnits +
    price * newUnits * newTax +
    ship
  ).toFixed(2);

  document.getElementById("finalShip").value = ship;
  document.getElementById("orderTotPrice").innerHTML = finalPrice;
  document.getElementById("finalPrice").value = finalPrice;
}

function difUnits() {
  var price = parseFloat(document.getElementById("orderPrice").innerHTML);
  var oldUnits = document.getElementById("units");
  var newUnits = parseFloat(oldUnits.value);
  var ship = parseFloat(document.getElementById("shipPrice").innerHTML);

  var tax = document.getElementById("taxPrice");
  var newTax = parseFloat(tax.innerHTML);
  console.log(newTax);

  if (newTax == "") {
    // if tax rate is empty ( i.e. user has not yet input zipcode, tax rate will be 0 )
    newTax = 0;
  }
  var finalPrice = (
    price * newUnits +
    price * newUnits * newTax +
    ship
  ).toFixed(2);
  document.getElementById("orderTotPrice").innerHTML = finalPrice;
  document.getElementById("finalPrice").value = finalPrice;
}

