package algorithms;

public class TextWrap {

	public static String wrapper(String text, int row_length) {

		char[] ch = text.toCharArray();
		StringBuilder sb = new StringBuilder();
		int rem_length = row_length;
		boolean newWord = false;
		boolean spaces = false;
		StringBuilder sub = new StringBuilder();

		if(row_length < 1) return "Column width should be 1 or greater.";
		
		if (row_length == 1) {
			int z = 0;
			while (z < text.length()) {
				sb.append(text.charAt(z) + "\n");
				z++;
			}
		} else {

			for (int i = 0; i < ch.length; i++) {
				if (rem_length > 1 && rem_length <= row_length) {

					if (ch[i] != ' ') {
						newWord = true;
					} else {
						spaces = true;
						newWord = false;
						if (sub.length() != 0) {
							sb.append(sub.toString());
							sub = new StringBuilder();
							rem_length = Math.abs(rem_length - sub.length());
						}
						if (rem_length != row_length) {
							sb.append(ch[i]);
							rem_length--;
						}
					}

					if (newWord) {
						if (spaces) {
							sub.append(ch[i]);
							rem_length--;
						} else {
							sb.append(ch[i]);
							rem_length--;
						}
					}

					if (i == text.length() - 1) {
						if (newWord && spaces) {
							sb.append(sub.toString());
							rem_length = Math.abs(row_length - sub.length());
						}
					}

				} else {
					if (newWord && spaces) {
						if (ch[i] == ' ') {
							if (sub.length() != 0) {
								sb.append(sub.toString() + "\n");
								sub = new StringBuilder();
								rem_length = row_length;
							}
						} else if (i == ch.length - 1) {
							sb.append(sub.toString() + ch[i]);
						} else if (i + 1 < ch.length && ch[i + 1] == ' ') {
							sb.append(sub.toString() + ch[i] + "\n");
							sub = new StringBuilder();
							rem_length = row_length;
							spaces = false;
						} else {
							if (sub.length() + 1 < row_length) {
								sb.append("\n" + sub.toString() + ch[i]);
								rem_length = Math.abs(row_length - (sub.toString().length() + 1));
							} else {
								int j = 0;
								rem_length = row_length;
								String temp = sub.toString();
								while (j < temp.length() && rem_length > 0) {
									sb.append(temp.charAt(j));
									rem_length--;
									if (rem_length == 0) {
										sb.append("\n");
										rem_length = row_length;
									}
									j++;
								}
								if (rem_length >= 1) {
									sb.append(ch[i]);
									rem_length--;
									if (rem_length == 0) {
										sb.append("\n");
										rem_length = row_length;
									}
								} else {
									sb.append("\n" + ch[i]);
									rem_length = row_length - 1;
								}
							}
							sub = new StringBuilder();
						}
						spaces = false;
					} else {
						if (i - 1 >= 0 && i + 1 < ch.length && ch[i - 1] == ' ' && ch[i + 1] != ' ') {
							sb.append("\n" + ch[i]);
							rem_length = Math.abs(row_length - 1);
							spaces = false;
						} else {
							sb.append(ch[i] + "\n");
							rem_length = row_length;
							spaces = false;
						}
					}
				}
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String text = "I'm from the University of Toledo and proficient in Java and Python.";
		System.out.println(wrapper(text, 8));
	}
}
