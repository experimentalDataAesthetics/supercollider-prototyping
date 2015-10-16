//Testing and exploring the synth

(
Pbindef(\grainplayer,
	\instrument, \grain3, \freq, 500,
	\sustain, 0.03, \pan, 0.0, \dur, 0.5, \amp, 0.8,
).play;
)

// change pattern
Pbindef(\grainplayer, \dur, Pn(Prand(#[0.1, 1.0, 4, 2, 0.5, 3], inf)));
Pbindef(\grainplayer, \dur, Pn(Prand(#[0.1, 0.4, 0.3, 2, 0.5, 0.3], inf)));


Pbindef(\grainplayer, \dur, 0.01);


// panning
Pbindef(\grainplayer, \pan, Pn(Pseq([-1.0, 1.0])));
Pbindef(\grainplayer, \pan, Pn(Pseq([-1.0, 1.0, 0.0, 0.5, -0.5])));

//multichannel
Pbindef(\grainplayer, \out, Pn(Pseq([0, 1, 2, 3])));

(
Pbindef(\grainplayer,
	\instrument, \grain4, \freq, 500,
	\sustain, 0.03, \pan, 0.0, \dur, 0.5, \amp, 0.8,
).play;
)

Pbindef(\grainplayer, \pan, 0);

// change grain durations
Pbindef(\grainplayer, \sustain, 0.1);
Pbindef(\grainplayer, \sustain, 0.03);
	//good values
Pbindef(\grainplayer, \sustain, 0.02,\amp, 0.02);
Pbindef(\grainplayer, \sustain, 0.03,\amp, 0.02); //**
Pbindef(\grainplayer, \sustain, 0.04,\amp, 0.02);
Pbindef(\grainplayer, \sustain, 0.1);

Pbindef(\grainplayer, \sustain, 0.01);
Pbindef(\grainplayer, \sustain, 0.003);
Pbindef(\grainplayer, \sustain, 0.001);
Pbindef(\grainplayer, \sustain, Pn(Pgeom(0.1, 0.9, 60)));
Pbindef(\grainplayer, \sustain, Pfunc({ exprand(0.0003, 0.03) }));
Pbindef(\grainplayer, \sustain, 0.03);
Pbindef(\grainplayer, \sustain, Pn(Pshuf([0.05, 0.01, 0.07, 0.03, 0.01])));


// change grain waveform (sine) frequency
Pbindef(\grainplayer, \freq, 50);
Pbindef(\grainplayer, \freq, 1000);
Pbindef(\grainplayer, \freq, 3000);
Pbindef(\grainplayer, \freq, Pn(Pgeom(300, 1.125, 32)));
Pbindef(\grainplayer, \freq, Pfunc({ exprand(30, 3000) }));
Pbindef(\grainplayer, \freq, 1000);
	// fixed order
Pbindef(\grainplayer, \freq, Pn(Pseq([1000, 600, 350, 250].scramble)));
	// different order every time
Pbindef(\grainplayer, \freq, Pn(Pshuf([10000, 100, 350, 250, 2000])));


// several frequencies by hand. Amount of frequencies (dimensions/attributes of a datapoint) is propotional aggregated amplitudes!
Pbindef(\grainplayer, \freq, [300, 1400, 100, 1000, 40, 10, 444, 22, 44, 5555, 400]);
Pbindef(\grainplayer, \freq, [300, 400, 100, 600, 40, 10, 10144, 22, 44, 5555, 400]);



Pbindef(\grainplayer, \freq, [300, 1400], \amp, 0.02);


// Testing with random datapoints
(
var dimensions, vol;
dimensions = 25;
vol = 1.0 / dimensions;
Pbindef(\grainplayer, \freq, Array.fill(dimensions,{11500.16.rand}),\amp, vol);
)

/*
What becomes audible?
==>> the "distribution of the attributes" of one datapoint
==>> simultaneous barchart of one data point (What about the order of playing. Now it is play all at once)

What else can we hear?
*/