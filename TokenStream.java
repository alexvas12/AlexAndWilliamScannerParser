Alex V
ghost_rider_alex
Online

William — 11/16/2024 12:23 AM
i can only find jay
its ok ill keep looking
Alex V — 11/16/2024 12:24 AM
oh
it’s in the homework
homework 4
word document
if that’s the one we talking abt
William — 11/16/2024 12:25 AM
perfect thanks
Alex V — 11/16/2024 12:27 AM
yep no problem
you are doing the concrete syntax rn?
how would you like me to assist you with it since we are technically working on it together?
lmk
William — 11/17/2024 6:19 PM
i may or may not have gotten caried away and done it on my own
Alex V — 11/17/2024 6:20 PM
oh
William — 11/17/2024 6:20 PM
sorry about that
i can explain all of it if you would like
Alex V — 11/17/2024 6:20 PM
no worries. We still working on it "together" tho or??
William — 11/17/2024 6:20 PM
yeah of cours
Alex V — 11/17/2024 6:20 PM
im sure id undesrstand what the code is after looking at it. If any mistakes i would be able to correct it.
lets look at it on tuesday or so? Ill be on campus monday and tuesday till lateish studying for the exam. most likely
Damn sorry I didnt work on itwith you!
William — 11/17/2024 6:22 PM
great
Alex V — 11/18/2024 2:17 PM
Run classroom-resources/autograding-command-grader@v1
  with:
    test-name: Scanner Test
    command: gradle test
    timeout: 10
Expand
message.txt
7 KB
Alex V — 11/18/2024 5:25 PM
Image
William — Yesterday at 12:58 AM
do you have a link to the evaluator
Alex V — Yesterday at 1:03 AM
you mean the github?
or what evaluator?
William — Yesterday at 1:03 AM
the github but i found it
Alex V — Yesterday at 1:03 AM
Okay. understood. Apologies, didn’t have my phone in hand
William — Yesterday at 1:05 AM
how do i make it run the test?
Alex V — Yesterday at 1:15 AM
you have to clone the repository, exchange the single  file  that you changed “concrete syntax “ or whichever it is. And push it back onto that github repository under your name
once that it done
go to the actions tab and there should be like a run button or something
by the way
did you successfully fix the scanner
or not yet?
William — Yesterday at 1:15 AM
working on it right now
Alex V — Yesterday at 1:15 AM
i don’t believe the parser will work if the scanner doesn’t work
okay. let me know once scanner is completed
William — Yesterday at 1:18 AM
ok so i fixed the code
i do not know how to test it
can i send it here then you can screen record testing it and ill be able to test it from now on
Alex V — Yesterday at 1:21 AM
unfortunately i can’t rn. I’m just about to get to sleep right now
i have a class tomorrow
i’ll be free after 3 pm
and we can test it
Alex V — Yesterday at 11:26 PM
i’m free to work on the project tmr. for the scanner and parser
William — Today at 12:52 PM
i can work till 4
Alex V — Today at 1:20 PM
unfortunately i’m out running errands. just send me the files and i’ll run them on github
William — Today at 1:21 PM
ok
Alex V — Today at 5:55 PM
can you send me the code?
William — Today at 6:26 PM
Sorry I will in a bit but I'm having some trouble right now
Alex V — Today at 6:26 PM
no worries
William — Today at 6:26 PM
When you run it on gh screen record so I can debug it myself
And send me the screen recording
Alex V — Today at 6:26 PM
if you want
i could just do it in front of you? on discord we can get into call or its better if screen recording?
William — Today at 6:28 PM
Screen recording would be better incase i forget what you did
Alex V — Today at 6:29 PM
so you wantme to just run it and screen recording
or screen record the entire process?
from opening to github to running it?
and both files
scanner + parser
William — Today at 11:00 PM
// TokenStream.java

// Implementation of the Scanner for JAY

// This code DOES NOT implement a scanner for JAY yet. You have to complete
// the code and also make sure it implements a scanner for JAY - not something
Expand
message.txt
8 KB
// ConcreteSyntax.java

// Implementation of the Recursive Descent Parser algorithm

//  Each method corresponds to a concrete syntax grammar rule, 
// which appears as a comment at the beginning of the method.
Expand
message.txt
10 KB
Alex V — Today at 11:12 PM
can you send me these files again but the .java file'
@William
easier that way
﻿
// TokenStream.java

// Implementation of the Scanner for JAY

// This code DOES NOT implement a scanner for JAY yet. You have to complete
// the code and also make sure it implements a scanner for JAY - not something
// else.

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TokenStream {

	// READ THE COMPLETE FILE FIRST
	// You will need to adapt it to KAY, NOT JAY

	// Instance variables 
	private boolean isEof = false; // is end of file
	private char nextChar = ' '; // next character in input stream
	private BufferedReader input;

	// This function was added to make the demo file work
	public boolean isEoFile() {
		return isEof;
	}

	// Constructor
	// Pass a filename for the program text as a source for the TokenStream.
	public TokenStream(String fileName) {
		try {
			input = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + fileName);
			// System.exit(1); // Removed to allow ScannerDemo to continue
			// running after the input file is not found.
			isEof = true;
		}
	}

	public Token nextToken() { // Main function of the scanner
								// Return next token type and value.
		Token t = new Token();
		t.setType("Other"); // For now it is Other
		t.setValue("");

		// First check for whitespaces and bypass them
		skipWhiteSpace();

		// Then check for a comment, and bypass it
		// but remember that / may also be a division operator.
		while (nextChar == '/') {
			// Changed if to while to avoid the 2nd line being printed when
			// there are two comment lines in a row.
			nextChar = readChar();
			if (nextChar == '/') { // If / is followed by another /
				// skip rest of line - it's a comment.
				// TODO TO BE COMPLETED W completed 
				while((int)nextChar!=10&&(int)nextChar!=12&&(int)nextChar!=13)
				{
					nextChar=readChar();
					
					 if (isEof) {
						 return t;
					 }
				}
				// look for <cr>, <lf>, <ff>

			} else {
				// A slash followed by anything else must be an operator.
				t.setValue("/");
				t.setType("Operator");
				return t;
			}
		}

		// Then check for an operator; this part of the code should recover 2-character
		// operators as well as 1-character ones.
		if (isOperator(nextChar)) {
			t.setType("Operator");
			t.setValue(t.getValue() + nextChar);
			/*
			if (nextChar=="<"||nextChar=="!"||nextChar==">"||nextChar=="=")
			{
				nextChar= readChar()
				if(nextChar=="=")
				{
					t.setValue(t.getValue() + nextChar);
				}
			}
			*/
			switch (nextChar) {
			// TODO TO BE COMPLETED WHERE NEEDED
			case ':':
				nextChar = readChar();
				if (nextChar == '=') {
					t.setValue(t.getValue() + nextChar);
					nextChar = readChar();
					return t;
				} else {
					t.setType("Other");
					nextChar=readChar();
				}
			case '<':
				// <=
				nextChar=readChar();
				if (nextChar=='=')
				{
					t.setValue(t.getValue()+nextChar);
					nextChar=readChar();
				}
				return t;
			case '>':
				// >=
				nextChar=readChar();
				if (nextChar=='=')
				{
					t.setValue(t.getValue()+nextChar);
					nextChar=readChar();
				}
				return t;
			case '=':
				// ==
				nextChar=readChar();
				if (nextChar=='=')
				{
					t.setValue(t.getValue()+nextChar);
					nextChar=readChar();
				}
				else
				{
					t.setType("Other");
				}
				return t;
			case '!':
				
				nextChar = readChar();
				if (nextChar=='=')
				{
					t.setValue(t.getValue()+nextChar);
					nextChar=readChar();
				}
				return t;
			case '|':
				// Look for ||
				nextChar = readChar();
				if (nextChar == '|') {
					t.setValue(t.getValue() + nextChar);
					nextChar = readChar();
					return t;
				} else {
					t.setType("Other");
					nextChar=readChar();
				}
				return t;

			case '&':
				// Look or &&
				nextChar = readChar();
				if (nextChar == '&') {
					t.setValue(t.getValue() + nextChar);
					nextChar = readChar();
					return t;
				} else {
					t.setType("Other");
					nextChar=readChar();
				}

				return t;
			
				
			default: // all other operators
				nextChar = readChar();
				return t;
			}
		}

		// Then check for a separator
		if (isSeparator(nextChar)) {
			t.setType("Separator");
			// TODO TO BE COMPLETED
			t.setValue(t.getValue() + nextChar);
			nextChar=readChar();

			return t;
		}

		// Then check for an identifier, keyword, or literal.
		if (isLetter(nextChar)) {
			// Set to an identifier
			t.setType("Identifier");
			while ((isLetter(nextChar) || isDigit(nextChar))) {
				t.setValue(t.getValue() + nextChar);
				nextChar = readChar();
			}
			// now see if this is a keyword
			if (isKeyword(t.getValue())) {
				t.setType("Keyword");
			} else if (t.getValue().equals("True") || t.getValue().equals("False")) {
				t.setType("Literal");
			}
			if (isEndOfToken(nextChar)) { // If token is valid, returns.
				return t;
			}
		}

		if (isDigit(nextChar)) { // check for integer literals
			t.setType("Literal");
			while (isDigit(nextChar)) {
				t.setValue(t.getValue() + nextChar);
				nextChar = readChar();
			}
			// An Integer-Literal is to be only followed by a space,
			// an operator, or a separator.
			if (isEndOfToken(nextChar)) {// If token is valid, returns.
				return t;
			} 
		}

		t.setType("Other");
		
		if (isEof) {
			return t;
		}

		// Makes sure that the whole unknown token (Type: Other) is printed.
		while (!isEndOfToken(nextChar)) {
			t.setValue(t.getValue() + nextChar);
			nextChar = readChar();
		}
		
		// Finally check for whitespaces and bypass them
		skipWhiteSpace();

		return t;
	}

	private char readChar() {
		int i = 0;
		if (isEof)
			return (char) 0;
		System.out.flush();
		try {
			i = input.read();
		} catch (IOException e) {
			System.exit(-1);
		}
		if (i == -1) {
			isEof = true;
			return (char) 0;
		}
		return (char) i;
	}

	private boolean isKeyword(String s) {
		if(s.equals("bool")||s.equals("else")||s.equals("if")||s.equals("integer")||s.equals("main")||s.equals("while"))
		{
			return true;
		}
		return false;
	}

	private boolean isWhiteSpace(char c) {
		return (c == ' ' || c == '\t' || c == '\r' || c == '\n' || c == '\f');
	}

	private boolean isEndOfLine(char c) {
		return (c == '\r' || c == '\n' || c == '\f');
	}

	private boolean isEndOfToken(char c) { // Is the value a seperate token?
		return (isWhiteSpace(nextChar) || isOperator(nextChar) || isSeparator(nextChar) || isEof);
	}

	private void skipWhiteSpace() {
		// check for whitespaces, and bypass them
		while (!isEof && isWhiteSpace(nextChar)) {
			nextChar = readChar();
		}
	}

	private boolean isSeparator(char c) {
		// TODO TO BE COMPLETED
		if (nextChar=='('||nextChar==')'||nextChar=='{'||nextChar=='}'||nextChar==';'||nextChar==',')
		{
			return true;
		}
		return false;
	}

	private boolean isOperator(char c) {
		// Checks for characters that start operators
		// TODO TO BE COMPLETED w completed 
		if (nextChar=='*'||nextChar=='-'||nextChar=='+'||nextChar=='<'||nextChar=='>'||nextChar=='|'||nextChar=='!'||nextChar=='&'||nextChar=='='||nextChar=='/'||nextChar==':')
		{
			return true;
		}
		return false;
	}

	private boolean isLetter(char c) {
		return (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z');
	}

	private boolean isDigit(char c) {
		// TODO TO BE COMPLETED
		if (48 <= (int)nextChar && (int)nextChar<=57)
		{
			return true;
		}
		return false;
	}

	public boolean isEndofFile() {
		return isEof;
	}
}
message.txt
8 KB
