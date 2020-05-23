function changeTax() {
  var zip = document.getElementById("zip").value;
  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      let data = JSON.parse(this.responseText);
      console.log(data);
      var total = parseFloat(document.getElementById("cartPrice").value);
      var taxRate = 0;
      for (var i = 0; i < data.length; ++i) {
        taxRate = parseFloat(data[i]["tax"]).toFixed(2);
        var newtaxRate = (
          parseFloat(document.getElementById("cartPrice").value) *
          parseFloat(taxRate)
        ).toFixed(2);
        document.getElementById("taxPrice").value = newtaxRate;
        document.getElementById("taxPrice").innerHTML = newtaxRate;
        var final = (parseFloat(total) + parseFloat(newtaxRate)).toFixed(2);
      }
      document.getElementById("taxCart").value = final;
      if (document.getElementById("shipCart").value != "") {
        var shippingCart = parseFloat(
          document.getElementById("shipCart").value
        );
        final = (parseFloat(shippingCart) + parseFloat(newtaxRate)).toFixed(2);
      }
      document.getElementById("orderTotPrice").innerHTML = final;
    }
  };
  xmlhttp.open("GET", "api/tax?zip=" + zip, true);
  console.log("sending");
  xmlhttp.send();
}

function checkState() {
  var state = document.getElementById("state").value;

  if (state == "") {
    var zip = document.getElementById("zip").value;
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        let data = JSON.parse(this.responseText);
        console.log(data);
        for (var i = 0; i < data.length; ++i) {
          document.getElementById("state").value = data[i]["state"];
        }
      }
    };
    xmlhttp.open("GET", "api/zipcode?zip=" + zip, true);
    console.log("sending");
    xmlhttp.send();
  }
}

function checkAndSet() {
  checkState();
  changeTax();
}
