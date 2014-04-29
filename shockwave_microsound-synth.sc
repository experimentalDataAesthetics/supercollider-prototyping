s.boot;
s.scope(2);
(
SynthDef("grain", { |out, amp=0.1, freq=440, sustain=0.01, pan|
	var snd = FSinOsc.ar(freq);
	var amp2 = amp * AmpComp.ir(freq.max(50)) * 0.5;
	var env = EnvGen.ar(Env.sine(sustain, amp2), doneAction: 2);
	OffsetOut.ar(out, Pan2.ar(snd * env, pan));
}, \ir ! 5).writeDefFile("/Applications/SuperCollider/SuperCollider.app/Contents/Resources/synthdefs/");

)
// very CPU efficient synthdef from p. 471, Supercollider Book, Chapter Microsound Alberto de Campo

(
SynthDef("grain", { |out, amp=0.1, freq=440, sustain=0.01, pan|
	var snd = FSinOsc.ar(freq);
	var amp2 = amp * AmpComp.ir(freq.max(50)) * 0.5;
	var env = EnvGen.ar(Env.sine(sustain, amp2), doneAction: 2);
	OffsetOut.ar(out, Pan2.ar(snd * env, pan));
}, \ir ! 5).add;

)
