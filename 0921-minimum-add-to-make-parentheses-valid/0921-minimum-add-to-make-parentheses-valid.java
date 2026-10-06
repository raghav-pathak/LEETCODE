class Solution {
    public int minAddToMakeValid(String s) {
        int openNeeded = 0;
        int balance = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                balance++;
            } else {
                if (balance > 0) {
                    balance--;
                } else {
                    openNeeded++;
                }
            }
        }

        return openNeeded + balance;
    }
}