var imgNum;
imgNum = 1;
showSlider(imgNum);

function showSlider(num) {
	var slider = document.getElementsByClassName('conntent')
	if (imgNum > slider.length ) { imgNum = 1; }
	if (imgNum < 1 ) { imgNum = slider.length; }
	for (var i = 0; i < slider.length ; i++) {
		slider[i].style.display = "none";
	}
	slider[imgNum-1].style.display = "block";
}

function nextPrev(num) {
	showSlider(num);
}
setInterval(function() {
	showSlider(imgNum += 1);
}, 3000);

var modal = document.getElementById('myModal');

var btn = document.getElementById("button");

var span = document.getElementsByClassName("close")[0];

btn.onclick = function() {
    modal.style.display = "block";
}

span.onclick = function() {
    modal.style.display = "none";
}

window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}