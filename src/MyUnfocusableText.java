
public class MyUnfocusableText extends MyTextComponent{
	MyUnfocusableText(int x, int y, int width, int height, String value) {
		super(x, y, width, height, value);
		textField.setFocusable(false);
	}
	
	MyUnfocusableText(int x, int y, int width, int height, String value, boolean useSmallFont) {
		this(x, y, width, height, value);
		if (useSmallFont){
			textField.setFont(Constants.SMALL_FONT);
		}
	}
}
