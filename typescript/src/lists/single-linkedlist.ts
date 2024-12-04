import { MayBeNull } from "../util.ts";

class SingleLinkedListNode<E> {
    val: E;
    next: MayBeNull<SingleLinkedListNode<E>>;

    constructor(val: E, next: MayBeNull<SingleLinkedListNode<E>> = null) {
        this.val = val;
        this.next = next;
    }
}

export default class SingleLinkedList<T> {
    private head: MayBeNull<SingleLinkedListNode<T>>;
    private tail: MayBeNull<SingleLinkedListNode<T>>;
    private internalSize: number;

    constructor() {
        this.head = null;
        this.tail = null;
        this.internalSize = 0;
    }

    private init(val: T) {
        const node = new SingleLinkedListNode(val);
        this.head = node;
        this.tail = this.head;
        this.internalSize++;
    }

    public get size() {
        return this.internalSize;
    }

    public isEmpty() {
        return this.internalSize == 0;
    }

    public clear() {
        this.head = null;
        this.tail = null;
        this.internalSize = 0;
    }

    public add(val: T, index: MayBeNull<number> = null) {
        if (this.isEmpty()) return this.init(val);
        if (!index) return this.addLast(val);
    }

    public addFirst(val: T) {
        if (this.isEmpty()) return this.init(val);
    }

    public addLast(val: T) {
        if (this.isEmpty()) return this.init(val);
    }
}
