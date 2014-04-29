//Read in cvs with already scaled (with numpy/ipython) data
x = CSVFileReader.read("/Users/shintaromiyazaki/Documents/SC-Code/supercollider-prototyping/wine_data_scaled_cvs.sc", true, true, (_.asFloat)).postcs;

//datapoint of row 1 (0 is occupied with the names)
x[0];


//Testing the synth with random values
(
var dimensions, vol;
dimensions = 14;
vol = 1.0 / dimensions;
Pbindef(\grainplayer, \instrument, \grain, \freq, x[70], \sustain, 0.03, \pan, 0.0, \dur, Pn(Prand(#[0.1, 0.6, 1.0, 4, 2, 0.5, 3, 1], inf)), \amp, vol).play;
)


