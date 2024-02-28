// script.js file
function domReady(fn) {
    if (
        document.readyState === "complete" ||
        document.readyState === "interactive"
    ) {
        setTimeout(fn, 1000);
    } else {
        document.addEventListener("DOMContentLoaded", fn);
    }
}

domReady(function () {
    // If found you qr code
    function onScanSuccess(decodeText, decodeResult) {
        alert("Product has id : " + decodeText, decodeResult);

        $.ajax({
            url: '/api/admin/sell-off/product-detail/search/' + decodeText,
            method: 'GET',
            success: function (response) {
                console.log(response);
                var id = Number(decodeText);
                chooseProductDetail(id);
                loadSpChiTiet(response);
            },
            error: function (xhr, status, error) {
                console.error('Error:', error);
            }
        });
    }

    let htmlscanner = new Html5QrcodeScanner(
        "my-qr-reader",
        {fps: 10, qrbos: 250, disableFlip: false}
    );
    htmlscanner.render(onScanSuccess);
});