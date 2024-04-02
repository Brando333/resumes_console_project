package org.brando.view;

public class OptionsManager {

    /**
     * Returns an array of two elements where the first element is a boolean value which indicates whether the flag '/b','/B'; '/e' , '/E' has be found
     * the second element is the class where the view to be shown is.
     *
     * @param prompt : the input text to check if the flag appear
     * @param view   : the view class that invokes this method
     * @return the array with the two mentioned elements where the {@code true} value in the first element
     * indicates the flag has be found, {@code false} value indicates no flag has be found.
     * The second element of the array {@link Class}, indicates the class where the next view to be shown is.
     */
    public static Object[] checkOptions(String prompt, Class view) {

        if (prompt.matches("/b|/B")) {
            if (view.getSimpleName().equals("LoginView") || view.getSimpleName().equals("SignInView") || view.getSimpleName().equals("HomeView")) {
                //the Class value in the array is one class backward in the hierarchy of the Class view
                return new Object[]{true, IndexView.class};
            }
            if (view.getSimpleName().equals("ResumesView")) {
                //the Class value in the array is one class backward in the hierarchy of the Class view
                return new Object[]{true, HomeView.class};
            }
        }
        if (prompt.matches("/e|/E")) {
            System.exit(0);
        }
        return new Object[]{false, view};

    }


}
