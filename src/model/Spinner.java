package model;

public class Spinner {

    // -----------------------------------------------------------------
	// Attributes
    // -----------------------------------------------------------------

    private boolean spin;

    // -----------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------

    /**
     * Name: Spinner
     * Constructor method of a spinner.
    */
    public Spinner() {
        spin = true;
    }

    /**
     * Name: getSpin
     * Method used to get the status of a spin.
     * @return A boolean with true if the spin is spinning, or with false otherwise.
    */
    public boolean getSpin() {
        return spin;
    }

    /**
     * Name: getSpin
     * Method used to update the status of a spin.
     * @param spin - status of a spin - spin = boolean, spin != null
    */
    public void setSpin(boolean spin) {
        this.spin = spin;
    }
}