
package metronome;

    public class TypeSound 
    {
        private final String    name;
        private final int       midiNote;

        public TypeSound(String name, int midiNote) 
        {
            this.name       = name;
            this.midiNote   = midiNote;
        }

        ///////////////////////////////////////////
        /////////// Getters & Setters 
        

		public int getMidiNote()    {return midiNote;   }
        public String getName()     {return name;       }

        @Override
        public String toString() {return name;}
        
    }
