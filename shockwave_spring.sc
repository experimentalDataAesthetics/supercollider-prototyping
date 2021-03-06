/*
/s_new

Create a new synth.
string	synth definition name
int	synth ID
int	add action (0,1,2, 3 or 4 see below)
int	add target ID

N *
int or string	a control index or name
float or int or string	floating point and integer arguments are interpreted as control value. a symbol argument consisting of the letter 'c' or 'a' (for control or audio) followed by the bus's index.

Create a new synth from a synth definition, give it an ID, and add it to the tree of nodes. There are four ways to add the node to the tree as determined by the add action argument which is defined as follows:

add actions:
    0	add the new node to the the head of the group specified by the add target ID.
    1	add the new node to the the tail of the group specified by the add target ID.
    2	add the new node just before the node specified by the add target ID.
    3	add the new node just after the node specified by the add target ID.
    4	the new node replaces the node specified by the add target ID. The target node is freed.

Controls may be set when creating the synth. The control arguments are the same as for the n_set command.

If you send /s_new with a synth ID of -1, then the server will generate an ID for you. The server reserves all negative IDs. Since you don't know what the ID is, you cannot talk to this node directly later. So this is useful for nodes that are of finite duration and that get the control information they need from arguments and buses or messages directed to their group. In addition no notifications are sent when there are changes of state for this node, such as /go, /end, /on, /off.

If you use a node ID of -1 for any other command, such as /n_map, then it refers to the most recently created node by /s_new (auto generated ID or not). This is how you can map the controls of a node with an auto generated ID. In a multi-client situation, the only way you can be sure what node -1 refers to is to put the messages in a bundle.

This message now supports array type tags ($[ and $]) in the control/value component of the OSC message. Arrayed control values are applied in the manner of n_setn (i.e., sequentially starting at the indexed or named control). See the Node Messaging helpfile.*/


(
SynthDef("spring", { | springfac = 15000, damp = 0.0008 amp = 0.5|

	var f; //modulated input force
	var s;
	var env = Env([0, 0.2, 0], [0.02, 0.02, 0.02],[-5, 5, 5]);


	f = Pulse.ar(0.5)*EnvGen.kr(env);
	s = Spring.ar(f, springfac, damp);
	DetectSilence.ar(s, doneAction:2);
	Out.ar([0,1],amp*s)}).writeDefFile("/Applications/SuperCollider/SuperCollider.app/Contents/Resources/synthdefs/");
)

// OSC message to send: "/s_new", "spring", -1 (SynthID), 0 (Action), 0 (TargetID),

(
{
	var f; //modulated input force
	var env = Env([0, 0.2, 0], [0.02, 0.02, 0.02],[-5, 5, 5]);
	f = Pulse.ar(0.5)*EnvGen.kr(env);
    Spring.ar(f, 15000, 0.0007);

}.plot;
)



