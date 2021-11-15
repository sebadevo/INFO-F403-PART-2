// DO NOT EDIT
// Generated by JFlex 1.8.2 http://jflex.de/
// source: LexicalAnalyzer.flex


// See https://github.com/jflex-de/jflex/issues/222
@SuppressWarnings("FallThrough")
class Lexer {

  /** This character denotes the end of file. */
  public static final int YYEOF = -1;

  /** Initial size of the lookahead buffer. */
  private static final int ZZ_BUFFERSIZE = 16384;

  // Lexical states.
  public static final int YYINITIAL = 0;
  public static final int CO_STATE = 2;
  public static final int co_STATE = 4;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = {
     0,  0,  1,  1,  2, 2
  };

  /**
   * Top-level table for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_TOP = zzUnpackcmap_top();

  private static final String ZZ_CMAP_TOP_PACKED_0 =
    "\1\0\37\u0100\1\u0200\267\u0100\10\u0300\u1020\u0100";

  private static int [] zzUnpackcmap_top() {
    int [] result = new int[4352];
    int offset = 0;
    offset = zzUnpackcmap_top(ZZ_CMAP_TOP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_top(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Second-level tables for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_BLOCKS = zzUnpackcmap_blocks();

  private static final String ZZ_CMAP_BLOCKS_PACKED_0 =
    "\12\0\1\1\2\2\1\3\32\0\1\4\1\5\1\6"+
    "\1\7\1\0\1\10\2\0\1\11\11\12\1\13\1\14"+
    "\1\15\1\16\1\17\1\20\1\0\2\21\1\22\13\21"+
    "\1\23\13\21\6\0\1\24\1\25\1\26\1\27\1\30"+
    "\1\31\1\32\1\33\1\34\2\21\1\35\1\36\1\37"+
    "\1\40\1\41\1\21\1\42\1\43\1\44\2\21\1\45"+
    "\1\21\1\46\1\21\12\0\1\2\u01a2\0\2\2\326\0"+
    "\u0100\2";

  private static int [] zzUnpackcmap_blocks() {
    int [] result = new int[1024];
    int offset = 0;
    offset = zzUnpackcmap_blocks(ZZ_CMAP_BLOCKS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_blocks(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /**
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\3\0\1\1\2\2\1\3\1\4\1\5\1\6\1\7"+
    "\2\10\1\11\1\12\1\13\1\14\1\15\15\16\1\2"+
    "\1\17\1\2\1\20\1\21\1\16\1\22\1\23\1\24"+
    "\4\16\1\25\4\16\1\26\3\16\1\27\1\30\1\16"+
    "\1\31\5\16\1\32\3\16\1\33\1\16\1\34\1\35"+
    "\1\16\1\36\1\16\1\37\1\16\1\40\1\41\1\42"+
    "\2\16\1\43";

  private static int [] zzUnpackAction() {
    int [] result = new int[81];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\47\0\116\0\165\0\165\0\234\0\165\0\165"+
    "\0\165\0\165\0\165\0\165\0\303\0\352\0\165\0\165"+
    "\0\165\0\165\0\u0111\0\u0138\0\u015f\0\u0186\0\u01ad\0\u01d4"+
    "\0\u01fb\0\u0222\0\u0249\0\u0270\0\u0297\0\u02be\0\u02e5\0\u030c"+
    "\0\165\0\u0333\0\165\0\u0111\0\u035a\0\u0111\0\u0111\0\u0111"+
    "\0\u0381\0\u03a8\0\u03cf\0\u03f6\0\u0111\0\u041d\0\u0444\0\u046b"+
    "\0\u0492\0\u0111\0\u04b9\0\u04e0\0\u0507\0\u052e\0\u0111\0\u0555"+
    "\0\u0111\0\u057c\0\u05a3\0\u05ca\0\u05f1\0\u0618\0\u0111\0\u063f"+
    "\0\u0666\0\u068d\0\u0111\0\u06b4\0\u0111\0\u0111\0\u06db\0\u0111"+
    "\0\u0702\0\u0111\0\u0729\0\u0111\0\u0111\0\u0111\0\u0750\0\u0777"+
    "\0\u0111";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[81];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /**
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\2\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13"+
    "\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\4"+
    "\1\23\1\24\2\23\1\25\1\26\1\27\1\30\1\31"+
    "\2\23\1\32\2\23\1\33\1\23\1\34\1\35\1\23"+
    "\1\36\1\37\1\23\22\4\1\40\1\5\24\4\1\41"+
    "\1\4\1\42\14\4\1\5\26\4\50\0\1\4\56\0"+
    "\2\15\52\0\1\43\41\0\2\23\6\0\26\23\11\0"+
    "\2\23\6\0\2\23\1\44\23\23\11\0\2\23\6\0"+
    "\7\23\1\45\15\23\1\46\11\0\2\23\6\0\17\23"+
    "\1\47\6\23\11\0\2\23\6\0\17\23\1\50\6\23"+
    "\11\0\2\23\6\0\14\23\1\51\1\23\1\52\7\23"+
    "\11\0\2\23\6\0\17\23\1\53\1\23\1\54\4\23"+
    "\11\0\2\23\6\0\10\23\1\55\15\23\11\0\2\23"+
    "\6\0\17\23\1\56\6\23\11\0\2\23\6\0\21\23"+
    "\1\57\4\23\11\0\2\23\6\0\7\23\1\60\16\23"+
    "\11\0\2\23\6\0\12\23\1\61\4\23\1\62\6\23"+
    "\11\0\2\23\6\0\12\23\1\63\13\23\23\0\1\41"+
    "\24\0\1\41\56\0\2\23\6\0\11\23\1\64\14\23"+
    "\11\0\2\23\6\0\22\23\1\65\3\23\11\0\2\23"+
    "\6\0\6\23\1\66\17\23\11\0\2\23\6\0\21\23"+
    "\1\67\4\23\11\0\2\23\6\0\17\23\1\70\6\23"+
    "\11\0\2\23\6\0\23\23\1\71\2\23\11\0\2\23"+
    "\6\0\13\23\1\72\12\23\11\0\2\23\6\0\3\23"+
    "\1\73\22\23\11\0\2\23\6\0\7\23\1\74\16\23"+
    "\11\0\2\23\6\0\13\23\1\75\12\23\11\0\2\23"+
    "\6\0\13\23\1\76\12\23\11\0\2\23\6\0\7\23"+
    "\1\77\16\23\11\0\2\23\6\0\10\23\1\100\2\23"+
    "\1\101\10\23\1\102\1\23\11\0\2\23\6\0\15\23"+
    "\1\103\10\23\11\0\2\23\6\0\16\23\1\104\7\23"+
    "\11\0\2\23\6\0\6\23\1\105\17\23\11\0\2\23"+
    "\6\0\16\23\1\106\7\23\11\0\2\23\6\0\14\23"+
    "\1\107\11\23\11\0\2\23\6\0\16\23\1\110\7\23"+
    "\11\0\2\23\6\0\17\23\1\111\6\23\11\0\2\23"+
    "\6\0\10\23\1\112\15\23\11\0\2\23\6\0\12\23"+
    "\1\113\13\23\11\0\2\23\6\0\23\23\1\114\2\23"+
    "\11\0\2\23\6\0\7\23\1\115\16\23\11\0\2\23"+
    "\6\0\21\23\1\116\4\23\11\0\2\23\6\0\13\23"+
    "\1\117\12\23\11\0\2\23\6\0\14\23\1\120\11\23"+
    "\11\0\2\23\6\0\7\23\1\121\16\23";

  private static int [] zzUnpackTrans() {
    int [] result = new int[1950];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** Error code for "Unknown internal scanner error". */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  /** Error code for "could not match input". */
  private static final int ZZ_NO_MATCH = 1;
  /** Error code for "pushback value was too large". */
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /**
   * Error messages for {@link #ZZ_UNKNOWN_ERROR}, {@link #ZZ_NO_MATCH}, and
   * {@link #ZZ_PUSHBACK_2BIG} respectively.
   */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state {@code aState}
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\3\0\2\11\1\1\6\11\2\1\4\11\16\1\1\11"+
    "\1\1\1\11\56\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[81];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** Input device. */
  private java.io.Reader zzReader;

  /** Current state of the DFA. */
  private int zzState;

  /** Current lexical state. */
  private int zzLexicalState = YYINITIAL;

  /**
   * This buffer contains the current text to be matched and is the source of the {@link #yytext()}
   * string.
   */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** Text position at the last accepting state. */
  private int zzMarkedPos;

  /** Current text position in the buffer. */
  private int zzCurrentPos;

  /** Marks the beginning of the {@link #yytext()} string in the buffer. */
  private int zzStartRead;

  /** Marks the last character in the buffer, that has been read from input. */
  private int zzEndRead;

  /**
   * Whether the scanner is at the end of file.
   * @see #yyatEOF
   */
  private boolean zzAtEOF;

  /**
   * The number of occupied positions in {@link #zzBuffer} beyond {@link #zzEndRead}.
   *
   * <p>When a lead/high surrogate has been read from the input stream into the final
   * {@link #zzBuffer} position, this will have a value of 1; otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /** Number of newlines encountered up to the start of the matched text. */
  private int yyline;

  /** Number of characters from the last newline up to the start of the matched text. */
  private int yycolumn;

  /** Number of characters up to the start of the matched text. */
  @SuppressWarnings("unused")
  private long yychar;

  /** Whether the scanner is currently at the beginning of a line. */
  @SuppressWarnings("unused")
  private boolean zzAtBOL = true;

  /** Whether the user-EOF-code has already been executed. */
  @SuppressWarnings("unused")
  private boolean zzEOFDone;


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Lexer(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Translates raw input code points to DFA table row
   */
  private static int zzCMap(int input) {
    int offset = input & 255;
    return offset == input ? ZZ_CMAP_BLOCKS[offset] : ZZ_CMAP_BLOCKS[ZZ_CMAP_TOP[input >> 8] | offset];
  }

  /**
   * Refills the input buffer.
   *
   * @return {@code false} iff there was new input.
   * @exception java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead - zzStartRead);

      /* translate stored positions */
      zzEndRead -= zzStartRead;
      zzCurrentPos -= zzStartRead;
      zzMarkedPos -= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length * 2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException(
          "Reader returned 0 characters. See JFlex examples/zero-reader for a workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
        if (numRead == requested) { // We requested too few chars to encode a full Unicode character
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        } else {                    // There is room in the buffer for at least one more char
          int c = zzReader.read();  // Expecting to read a paired low surrogate char
          if (c == -1) {
            return true;
          } else {
            zzBuffer[zzEndRead++] = (char)c;
          }
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }


  /**
   * Closes the input reader.
   *
   * @throws java.io.IOException if the reader could not be closed.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true; // indicate end of file
    zzEndRead = zzStartRead; // invalidate buffer

    if (zzReader != null) {
      zzReader.close();
    }
  }


  /**
   * Resets the scanner to read from a new input stream.
   *
   * <p>Does not close the old reader.
   *
   * <p>All internal variables are reset, the old input stream <b>cannot</b> be reused (internal
   * buffer is discarded and lost). Lexical state is set to {@code ZZ_INITIAL}.
   *
   * <p>Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader The new input stream.
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzEOFDone = false;
    yyResetPosition();
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE) {
      zzBuffer = new char[ZZ_BUFFERSIZE];
    }
  }

  /**
   * Resets the input position.
   */
  private final void yyResetPosition() {
      zzAtBOL  = true;
      zzAtEOF  = false;
      zzCurrentPos = 0;
      zzMarkedPos = 0;
      zzStartRead = 0;
      zzEndRead = 0;
      zzFinalHighSurrogate = 0;
      yyline = 0;
      yycolumn = 0;
      yychar = 0L;
  }


  /**
   * Returns whether the scanner has reached the end of the reader it reads from.
   *
   * @return whether the scanner has reached EOF.
   */
  public final boolean yyatEOF() {
    return zzAtEOF;
  }


  /**
   * Returns the current lexical state.
   *
   * @return the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state.
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   *
   * @return the matched text.
   */
  public final String yytext() {
    return new String(zzBuffer, zzStartRead, zzMarkedPos-zzStartRead);
  }


  /**
   * Returns the character at the given position from the matched text.
   *
   * <p>It is equivalent to {@code yytext().charAt(pos)}, but faster.
   *
   * @param position the position of the character to fetch. A value from 0 to {@code yylength()-1}.
   *
   * @return the character at {@code position}.
   */
  public final char yycharat(int position) {
    return zzBuffer[zzStartRead + position];
  }


  /**
   * How many characters were matched.
   *
   * @return the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * <p>In a well-formed scanner (no or only correct usage of {@code yypushback(int)} and a
   * match-all fallback rule) this method will only be called with things that
   * "Can't Possibly Happen".
   *
   * <p>If this method is called, something is seriously wrong (e.g. a JFlex bug producing a faulty
   * scanner etc.).
   *
   * <p>Usual syntax/scanner level error handling should be done in error fallback rules.
   *
   * @param errorCode the code of the error message to display.
   */
  private static void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    } catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * <p>They will be read again by then next call of the scanning method.
   *
   * @param number the number of characters to be read again. This number must not be greater than
   *     {@link #yylength()}.
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }




  /**
   * Resumes scanning until the next regular expression is matched, the end of input is encountered
   * or an I/O-Error occurs.
   *
   * @return the next token.
   * @exception java.io.IOException if any I/O-Error occurs.
   */
  public Symbol yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char[] zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':  // fall through
        case '\u000C':  // fall through
        case '\u0085':  // fall through
        case '\u2028':  // fall through
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is
        // (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof)
            zzPeek = false;
          else
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMap(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
          { 	return new Symbol(LexicalUnit.END_OF_STREAM, yyline, yycolumn, yytext());
 }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1:
            { 
            }
            // fall through
          case 36: break;
          case 2:
            { System.out.print(yytext());
            }
            // fall through
          case 37: break;
          case 3:
            { return new Symbol(LexicalUnit.LPAREN,yyline, yycolumn,yytext());
            }
            // fall through
          case 38: break;
          case 4:
            { return new Symbol(LexicalUnit.RPAREN,yyline, yycolumn,yytext());
            }
            // fall through
          case 39: break;
          case 5:
            { return new Symbol(LexicalUnit.TIMES,yyline, yycolumn,yytext());
            }
            // fall through
          case 40: break;
          case 6:
            { return new Symbol(LexicalUnit.PLUS,yyline, yycolumn,yytext());
            }
            // fall through
          case 41: break;
          case 7:
            { return new Symbol(LexicalUnit.MINUS,yyline, yycolumn,yytext());
            }
            // fall through
          case 42: break;
          case 8:
            { return new Symbol(LexicalUnit.NUMBER,yyline, yycolumn,yytext());
            }
            // fall through
          case 43: break;
          case 9:
            { return new Symbol(LexicalUnit.DIVIDE,yyline, yycolumn,yytext());
            }
            // fall through
          case 44: break;
          case 10:
            { return new Symbol(LexicalUnit.SEMICOLON,yyline, yycolumn,yytext());
            }
            // fall through
          case 45: break;
          case 11:
            { return new Symbol(LexicalUnit.SMALLER,yyline, yycolumn,yytext());
            }
            // fall through
          case 46: break;
          case 12:
            { return new Symbol(LexicalUnit.EQUAL,yyline, yycolumn,yytext());
            }
            // fall through
          case 47: break;
          case 13:
            { return new Symbol(LexicalUnit.GREATER,yyline, yycolumn,yytext());
            }
            // fall through
          case 48: break;
          case 14:
            { return new Symbol(LexicalUnit.VARNAME, yyline, yycolumn, yytext());
            }
            // fall through
          case 49: break;
          case 15:
            { yybegin(YYINITIAL);
            }
            // fall through
          case 50: break;
          case 16:
            { return new Symbol(LexicalUnit.ASSIGN,yyline, yycolumn,yytext());
            }
            // fall through
          case 51: break;
          case 17:
            { yybegin(CO_STATE);
            }
            // fall through
          case 52: break;
          case 18:
            { return new Symbol(LexicalUnit.BY,yyline, yycolumn,yytext());
            }
            // fall through
          case 53: break;
          case 19:
            { yybegin(co_STATE);
            }
            // fall through
          case 54: break;
          case 20:
            { return new Symbol(LexicalUnit.DO,yyline, yycolumn,yytext());
            }
            // fall through
          case 55: break;
          case 21:
            { return new Symbol(LexicalUnit.IF,yyline, yycolumn,yytext());
            }
            // fall through
          case 56: break;
          case 22:
            { return new Symbol(LexicalUnit.TO,yyline, yycolumn,yytext());
            }
            // fall through
          case 57: break;
          case 23:
            { return new Symbol(LexicalUnit.END,yyline, yycolumn,yytext());
            }
            // fall through
          case 58: break;
          case 24:
            { return new Symbol(LexicalUnit.FOR,yyline, yycolumn,yytext());
            }
            // fall through
          case 59: break;
          case 25:
            { return new Symbol(LexicalUnit.NOT,yyline, yycolumn,yytext());
            }
            // fall through
          case 60: break;
          case 26:
            { return new Symbol(LexicalUnit.ELSE,yyline, yycolumn,yytext());
            }
            // fall through
          case 61: break;
          case 27:
            { return new Symbol(LexicalUnit.FROM,yyline, yycolumn,yytext());
            }
            // fall through
          case 62: break;
          case 28:
            { return new Symbol(LexicalUnit.READ,yyline, yycolumn,yytext());
            }
            // fall through
          case 63: break;
          case 29:
            { return new Symbol(LexicalUnit.THEN,yyline, yycolumn,yytext());
            }
            // fall through
          case 64: break;
          case 30:
            { return new Symbol(LexicalUnit.BEG,yyline, yycolumn,yytext());
            }
            // fall through
          case 65: break;
          case 31:
            { return new Symbol(LexicalUnit.ENDIF,yyline, yycolumn,yytext());
            }
            // fall through
          case 66: break;
          case 32:
            { return new Symbol(LexicalUnit.PRINT,yyline, yycolumn,yytext());
            }
            // fall through
          case 67: break;
          case 33:
            { return new Symbol(LexicalUnit.WHILE,yyline, yycolumn,yytext());
            }
            // fall through
          case 68: break;
          case 34:
            { return new Symbol(LexicalUnit.ENDFOR,yyline, yycolumn,yytext());
            }
            // fall through
          case 69: break;
          case 35:
            { return new Symbol(LexicalUnit.ENDWHILE,yyline, yycolumn,yytext());
            }
            // fall through
          case 70: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }

  /**
   * Runs the scanner on input files.
   *
   * This is a standalone scanner, it will print any unmatched
   * text to System.out unchanged.
   *
   * @param argv   the command line, contains the filenames to run
   *               the scanner on.
   */
  public static void main(String[] argv) {
    if (argv.length == 0) {
      System.out.println("Usage : java Lexer [ --encoding <name> ] <inputfile(s)>");
    }
    else {
      int firstFilePos = 0;
      String encodingName = "UTF-8";
      if (argv[0].equals("--encoding")) {
        firstFilePos = 2;
        encodingName = argv[1];
        try {
          // Side-effect: is encodingName valid?
          java.nio.charset.Charset.forName(encodingName);
        } catch (Exception e) {
          System.out.println("Invalid encoding '" + encodingName + "'");
          return;
        }
      }
      for (int i = firstFilePos; i < argv.length; i++) {
        Lexer scanner = null;
        try {
          java.io.FileInputStream stream = new java.io.FileInputStream(argv[i]);
          java.io.Reader reader = new java.io.InputStreamReader(stream, encodingName);
          scanner = new Lexer(reader);
          while ( !scanner.zzAtEOF ) scanner.yylex();
        }
        catch (java.io.FileNotFoundException e) {
          System.out.println("File not found : \""+argv[i]+"\"");
        }
        catch (java.io.IOException e) {
          System.out.println("IO error scanning file \""+argv[i]+"\"");
          System.out.println(e);
        }
        catch (Exception e) {
          System.out.println("Unexpected exception:");
          e.printStackTrace();
        }
      }
    }
  }


}
