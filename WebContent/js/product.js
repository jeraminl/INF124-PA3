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

function fillInfo() {
  var params = new URLSearchParams(window.location.search);
  var productID = params.get("Id");

  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("productInfo").innerHTML = this.responseText;
      document.getElementById("shipPrice").innerHTML = 5;
      document.getElementById(
        "orderProdID"
      ).innerHTML = document.getElementById("productID").innerText;
      document.getElementById("orderPrice").innerHTML = document.getElementById(
        "price"
      ).innerText;
      document.getElementById("orderTotPrice").innerHTML =
        "Complete form to get total price";
    }
    console.log("info filled");
  };
  xmlhttp.open("GET", "./php/fill_product_info.php?Id=" + productID, true);
  console.log("sending");
  xmlhttp.send();
}

function changeTax() {
  var zip = document.getElementById("zip").value;
  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("taxPrice").innerText = this.responseText;
    }
  };
  xmlhttp.open("GET", "./php/get_tax_rate.php?zip=" + zip, true);
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
        document.getElementById("state").value = this.responseText;
      }
    };

    xmlhttp.open("GET", "./php/get_state.php?zip=" + zip, true);
    console.log("sending");
    xmlhttp.send();
  }
}

function checkAndSet() {
  checkState();
  changeTax();
}
