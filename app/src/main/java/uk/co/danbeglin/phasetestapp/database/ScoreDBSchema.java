package uk.co.danbeglin.phasetestapp.database;

/**
 * Created by danny on 28/04/16.
 */
public class ScoreDBSchema {
    public static final class ScoreTable{
        public static final String NAME = "scores";

        public static final class Coloums {

            public static final String uuid = "scoreid";
            public static final String USER = "username";
            public static final String PASS = "password";
            public static final String SCORE = "finalscore";

        }


    }
}
