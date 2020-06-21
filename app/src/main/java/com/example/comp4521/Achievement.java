//COMP 4521 TING Kai Chung 20435275 kcting
//COMP 4521 CHEN Yu feng 20492352 ychenec
//COMP 4521 KONG Wai Yong 2462462 wykongaa

package com.example.comp4521;    //THIS IS THE ACHIEVEMENT CLASS


    class Achievement {
        // Member variables representing the title and information about the sport.
        private String title;
        private String info;
        private final int imageResource;


        /**
         * Constructor for the Sport data model.
         *
         * @param title The name if the sport.
         * @param info Information about the sport.
         */
        Achievement(String title, String info, int imageResource) {
            this.title = title;
            this.info = info;
            this.imageResource = imageResource;
        }

        /**
         * Gets the title of the sport.
         *
         * @return The title of the sport.
         */
        String getTitle() {
            return title;
        }

        /**
         * Gets the info about the sport.
         *
         * @return The info about the sport.
         */
        String getInfo() {
            return info;
        }

        public int getImageResource() {
            return imageResource;
        }

    }

