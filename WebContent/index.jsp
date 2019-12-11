<style>
@import 'https://fonts.googleapis.com/css?family=Merriweather';

/* Example row */
#row {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	height: 100vh;
	-webkit-font-smoothing: antialiased;
	background-blend-mode: multiply;
	background-size: cover;
	background-position: center;
	color: white;
}

#heading {
	font-weight: 600;
	font-size: 4rem;
	margin-bottom: 0;
	outline: none;
}

#strapline {
	font-weight: 300;
	font-size: 1.4rem;
	margin-top: .4rem;
	outline: none;
}

.button {
	font-size: 1rem;
	padding: .5rem 1.1rem .4rem;
	border: 1px solid;
	border-radius: 1rem;
	background: none;
	color: inherit;
	display: inline-block;
	text-decoration: none;
}

/* Fonts */
.Merriweather {
	font-family: "Merriweather", Georgia, serif;
}
</style>

<div id="row" style="background-image: url('/Dealership/images/crazy-carl-gif.gif');">
	<h1 id="heading" contenteditable="true">Crazy Carl's Crazy Cars</h1>
	<p id="strapline" contenteditable="true">Home of the craziest
		cars with the craziest deals.</p>
	<span><a class="button"
		href="/Dealership/Home">Browse Now</a></span>
</div>

<script>
	//Get elements
	var row = document.getElementById("row");
	var heading = document.getElementById("heading");
	var strapline = document.getElementById("strapline");

	// Fonts array
	var fonts = [ 'Merriweather' ];

	// Randomise styles
	var randHeading = fonts[Math.floor(Math.random() * fonts.length)];
	var randStrapline = fonts[Math.floor(Math.random() * fonts.length)];

	// Apply random styles
	row.style.color = randomColor({
		luminosity : 'light'
	});
	row.style.backgroundColor = randomColor({
		luminosity : 'dark'
	});
	row.style.backgroundImage = "/Dealership/images/crazy-carl-gif.gif";

	heading.setAttribute("class", randHeading);
	strapline.setAttribute("class", randStrapline);
</script>

