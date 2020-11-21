// Copyright (C) 2020 Jarmo Hurri

// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.

// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.

// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <https://www.gnu.org/licenses/>.

import java.util.Stack;
import java.util.EnumMap;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;

public class TowerOfHanoi
{
  public TowerOfHanoi (int numDiscs)
  {
    towers = new EnumMap<> (Peg.class);
    for (Peg peg : Peg.values ())
      towers.put (peg, new Stack<> ());
    
    for (int i = numDiscs; i >= 1; i--)
      towers.get (Peg.A).push (i);
    
    this.numDiscs = numDiscs;
    moves = new LinkedList <> ();
  }

  public void move (Peg from, Peg to) throws IllegalTowerOfHanoiMoveException
  {
    if (isEmpty (from) || !isEmpty (to) && peek (from) > peek (to))
      throw new IllegalTowerOfHanoiMoveException ("illegal move: " + from + " -> " + to);
    
    towers.get (to).push (towers.get (from).pop ());
    moves.add (new Move (from, to));
  }
    
  public int getNumDiscs ()
  {
    return numDiscs;
  }
  
  public Integer[] getDiscs (Peg peg)
  {
    return towers.get (peg).toArray (new Integer [0]);
  }
  
  public boolean isEmpty (Peg peg)
  {
    return towers.get (peg).empty ();
  }
  
  public int peek (Peg peg)
  {
    if (isEmpty (peg))
      return 0;
    else
      return (towers.get (peg).peek ());
  }
  
  public Queue<Move> getMoves ()
  {
    return moves;
  }
  
  public String toString ()
  {
    StringBuffer strBuf = new StringBuffer ();
    for (Peg peg : Peg.values ())
      strBuf.append (peg + ": " + towers.get (peg) + " \n");

    return strBuf.toString ();
      
  }
  
  private Map<Peg,Stack<Integer>> towers;
  private Queue<Move> moves;
  private int numDiscs;
}

enum Peg
{
  A, B, C;
}

class IllegalTowerOfHanoiMoveException extends Exception
{
  IllegalTowerOfHanoiMoveException (String s)
  {
    super (s);
  }
}
