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