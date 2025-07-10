package DSA_pactice;

public class AlternateSLL {
    static class Node {
        int i;
        Node next;

        public Node(int i) {
            this.i = i;
            this.next = null;
        }

        public Node(int i, Node next) {
            this.i = i;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node pre = head;
        for (int i = 2; i <= 8; i++) {
            Node node = new Node(i);
            pre.next = node;
            pre = node;
        }
        pre = head;
        while (pre != null) {
            System.out.print(pre.i + " ");
            pre = pre.next;
        }
        System.out.println();
        alternate(head);
        pre = head;
        while (pre != null) {
            System.out.print(pre.i + " ");
            pre = pre.next;
        }
    }

    private static Node alternate(Node head) {
        if (head == null || head.next == null) return head;

        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node j = reverse(slow.next);
        slow.next = null;
        Node i = head;

        merge(i, j);
        return head;
    }

    private static void merge(Node i, Node j) {
        while (i != null && j != null) {
            Node temp = i.next;
            Node temp2 = j.next;
            i.next = j;
            j.next = temp;
            i = temp;
            j = temp2;
        }
    }

    private static Node reverse(Node head) {
        Node pre = null;
        Node start = head;
        while (start != null) {
            Node temp = start.next;
            start.next = pre;
            pre = start;
            start = temp;
        }
        return pre;
    }
}
