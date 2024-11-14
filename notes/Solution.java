package notes;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
    }

    public ListNode deleteDuplicates (ListNode head) {
        // write code here
        ListNode p = head == null ? null : head.next, pre = head;
        while (p != null) {
            while (pre.val == p.val) p = p.next;
            pre.next = p;
        }
        return head;
    }
}