class TrieNode {
  isWordUntil: boolean;
  map: Map<string, TrieNode>;
  constructor() {
    this.isWordUntil = false;
    this.map = new Map();
  }
}

export default class PrefixTree {
  private root: TrieNode = new TrieNode();
  private wordCount: number = 0;

  public addWord(word: string) {
    let node = this.root;
    for (const char of word) {
      if (!node.map.has(char)) node.map.set(char, new TrieNode());
      node = node.map.get(char)!;
    }
    this.wordCount++;
    node.isWordUntil = true;
  }

  public removeWord(word: string) {
    let node = this.root;
    for (const char of word) {
      if (!node.map.has(char)) return;
      node = node.map.get(char)!;
    }
    this.wordCount--;
    node.isWordUntil = false;
  }

  public hasWord(word: string) {
    let node = this.root;
    for (const char of word) {
      if (!node.map.has(char)) return false;
      node = node.map.get(char)!;
    }
    return node.isWordUntil;
  }

  public hasPrefix(prefix: string) {
    let node = this.root;
    for (const char of prefix) {
      if (!node.map.has(char)) return false;
      node = node.map.get(char)!;
    }
    return true;
  }

  public count() {
    return this.wordCount;
  }

  public countWordsWithPrefix(prefix: string) {
    return this.getWordsWithPrefix(prefix).length;
  }

  public getWordsWithPrefix(prefix: string) {
    const ans: string[] = [];
    let node = this.root;
    for (const char of prefix) {
      if (!node.map.has(char)) return ans;
      node = node.map.get(char)!;
    }
    if (node.isWordUntil) ans.push(prefix);
    node.map.forEach((v, k) => {
      this.getWordsWithPrefixHelper(prefix + k, v, ans);
    });
    return ans;
  }

  public getWordsWithPrefixHelper(
    prefix: string,
    node: TrieNode,
    ans: string[],
  ) {
    if (node.isWordUntil) ans.push(prefix);
    node.map.forEach((v, k) => {
      this.getWordsWithPrefixHelper(prefix + k, v, ans);
    });
  }
}
