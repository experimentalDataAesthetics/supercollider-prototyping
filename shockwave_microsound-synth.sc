(
SynthDef("grain", { |out, amp=0.1, freq=440, sustain=0.01, pan|
	var snd = FSinOsc.ar(freq);
	var amp2 = amp * AmpComp.ir(freq.max(50)) * 0.5;
	var env = EnvGen.ar(Env.sine(sustain, amp2), doneAction: 2);
	OffsetOut.ar(out, Pan2.ar(snd * env, pan));
}, \ir ! 5).writeDefFile("/Applications/SuperCollider/SuperCollider.app/Contents/Resources/synthdefs/");

)

(
SynthDef("grain", { |out, amp=0.1, freq=440, sustain=0.01, pan|
	var snd = FSinOsc.ar(freq);
	var amp2 = amp * AmpComp.ir(freq.max(50)) * 0.5;
	var env = EnvGen.ar(Env.sine(sustain, amp2), doneAction: 2);
	OffsetOut.ar(out, Pan2.ar(snd * env, pan));
}, \ir ! 5).add;

)

(
s.sendMsg("s_new", \grain, -1, 0, 1, \freq, 200, \sustain, 0.1, \pan, -1.0); // even more efficient, as no Synth object is created.
s.sendMsg("s_new", \grain, -1, 0, 1, \freq, 1200, \sustain, 0.1, \pan, 1.0);
s.sendMsg("s_new", \grain, -1, 0, 1, \freq, 1400, \sustain, 0.1, \pan, -0.75);
s.sendMsg("s_new", \grain, -1, 0, 1, \freq, 1800, \sustain, 0.1, \pan, -0.5);
s.sendMsg("s_new", \grain, -1, 0, 1, \freq, 2200, \sustain, 0.1, \pan, 0.5);
s.sendMsg("s_new", \grain, -1, 0, 1, \freq, 600, \sustain, 0.1, \pan, 0.75);
s.sendMsg("s_new", \grain, -1, 0, 1, \freq, 100, \sustain, 0.1, \pan, 1.0);
)

// very CPU efficient synthdef from p. 471, Supercollider Book, Chapter Microsound Alberto de Campo


s.scope;

//Testing

(
SynthDef(\gabor1, { |out, amp=0.1, freq=440, sustain=0.01, pan|
	var snd = FSinOsc.ar(freq);
	var amp2 = amp * AmpComp.ir(freq.max(50)) * 0.5;
	var env = EnvGen.ar(Env.sine(sustain, amp2), doneAction: 2);
	OffsetOut.ar(out, Pan2.ar(snd * env, pan));
}, \ir ! 5).add;
)

(
Pbindef(\grain0,
	\instrument, \gabor1, \freq, 500,
	\sustain, 0.01, \dur, 0.2
).play;
)
	// change grain durations
Pbindef(\grain0, \sustain, 0.1);
Pbindef(\grain0, \sustain, 0.03);
Pbindef(\grain0, \sustain, 0.01);
Pbindef(\grain0, \sustain, 0.003);
Pbindef(\grain0, \sustain, 0.001);
Pbindef(\grain0, \sustain, Pn(Pgeom(0.1, 0.9, 60)));
Pbindef(\grain0, \sustain, Pfunc({ exprand(0.0003, 0.03) }));
Pbindef(\grain0, \sustain, 0.03);

	// change grain waveform (sine) frequency
Pbindef(\grain0, \freq, 300);
Pbindef(\grain0, \freq, 1000);
Pbindef(\grain0, \freq, 3000);
Pbindef(\grain0, \freq, Pn(Pgeom(300, 1.125, 32)));
Pbindef(\grain0, \freq, Pfunc({ exprand(300, 3000) }));
Pbindef(\grain0, \freq, 1000);

	// fixed order
Pbindef(\grain0, \freq, Pn(Pseq([1000, 600, 350, 250].scramble)));

	// different order every time
Pbindef(\grain0, \freq, Pn(Pshuf([1000, 600, 350, 250])));
