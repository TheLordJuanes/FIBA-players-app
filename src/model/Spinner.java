package model;

public class Spinner {

    // -----------------------------------------------------------------
	// Attributes
    // -----------------------------------------------------------------

    private boolean spin;

    // -----------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------

    public Spinner() {
        spin = true;
    }

    /**
     * @return boolean return the spin
     */
    public boolean getSpin() {
        return spin;
    }

    /**
     * @param spin the spin to set
     */
    public void setSpin(boolean spin) {
        this.spin = spin;
    }
}