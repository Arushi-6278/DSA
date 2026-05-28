class Solution {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        
        int bestIndex = -1; 
    }

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        TrieNode root = new TrieNode();
        int globalBestIndex = 0;
        
       
        for (int i = 1; i < wordsContainer.length; i++) {
            if (wordsContainer[i].length() < wordsContainer[globalBestIndex].length()) {
                globalBestIndex = i;
            }
        }
        root.bestIndex = globalBestIndex;

        // Step 2: Build the Trie with reversed words
        for (int i = 0; i < wordsContainer.length; i++) {
            String word = wordsContainer[i];
            TrieNode curr = root;
            
          
            for (int j = word.length() - 1; j >= 0; j--) {
                int charIdx = word.charAt(j) - 'a';
                if (curr.children[charIdx] == null) {
                    curr.children[charIdx] = new TrieNode();
                }
                curr = curr.children[charIdx];
                
                
                if (curr.bestIndex == -1 || word.length() < wordsContainer[curr.bestIndex].length()) {
                    curr.bestIndex = i;
                }
            }
        }

       
        int[] ans = new int[wordsQuery.length];
        for (int i = 0; i < wordsQuery.length; i++) {
            String query = wordsQuery[i];
            TrieNode curr = root;
         
            for (int j = query.length() - 1; j >= 0; j--) {
                int charIdx = query.charAt(j) - 'a';
                if (curr.children[charIdx] == null) {
                    break; 
                }
                curr = curr.children[charIdx];
            }
            
            ans[i] = curr.bestIndex;
        }

        return ans;
    }
}