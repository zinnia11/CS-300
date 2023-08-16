
public interface TankListener {
  /**
   * Draws this tank object to the display window
   */
  public void draw();
  
  /**
   * Method called each time the mouse is Pressed
   */
  public void mousePressed();

  /**
   * Method called each time the mouse is Released
   */
  public void mouseReleased();
  
  /**
   * Checks whether the mouse is over this Tank GUI
   * <p>
   * @return true if the mouse is over this Tank GUI object false otherwise
   */
  public boolean isMouseOver();
  
}
