package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class BinaryTree<E extends Comparable<E>> implements Serializable {
    private Node<E> root; 

    public BinaryTree() {
        root = null;
    }

    public void add(E data) {
        Node<E> newNode = new Node<E>(data);
        if (root == null) {
            root = newNode;
        } else {
            add(root, newNode);
        }
    }

    private void add(Node<E> r, Node<E> newNode) {
        if (newNode.data.compareTo(r.data) < 0) {
            if (r.left == null) {
                r.left = newNode;
            } else {
                add(r.left, newNode);
            }
        } else {
            if (newNode.data.compareTo(r.data) > 0) {
                if (r.right == null) {
                    r.right = newNode;
                } else {
                    add(r.right, newNode);
                }
            }
        }
    }

    public E upper(Node<E> r){
        if(r.right != null)
            return upper(r.right);
        else
            return r.data;
    }
    
    public E minor(Node<E> r){
        if(r.left!=null)
            return minor(r.left);
        else
            return r.data;
    }

    public boolean search(E data) {
        if (root == null) {
            return false;
        } else {
            return search(root, data);
        }
    }

    private boolean search(Node<E> r, E data) {
        if (r == null) {
            return false;
        } else {
            if (data.compareTo(r.data) == 0) {
                return true;
            } else {
                if (data.compareTo(r.data) < 0) {
                    return search(r.left, data);
                } else {
                    return search(r.right, data);
                }
            }
        }
    }

    public void delete(E data) {
        if (search(data)) {
            root = delete(root, data);
        }
    }

    private Node<E> delete(Node<E> r, E data) {
        if (r.data.compareTo(data) == 0) {
            return deleteNode(r, data);
        } else {
            if (data.compareTo(r.data) < 0) {
                // Left
                r.left = delete(r.left, data);
            } else {
                // Right
                r.right = delete(r.right, data);
            }
            return r;

        }
    }

    private Node<E> deleteNode(Node<E> r, E data) {
        // Case 1: No children
        if (r.left == null && r.right == null) {
            return null;
        } else {
            // Case 2: One child
            if (r.left == null) {
                return r.right;
            } else {
                if (r.right == null) {
                    return r.left;
                } else {
                    // Case 3: Two children
                    E may = upper(r.left);
                    r.data = may;
                    r.left = delete(r.left, may);
                    return r;
                }
            }
        }
    }

    public int weight(Node<E> r) {
        if (r != null) {
            return 1 + weight(r.left) + weight(r.right);
        } else {
            return 0;
        }
    }

    public int height(Node<E> r) {
        if (r == null) {
            return 0;
        } else {
            return  1 + Integer.max(height(r.left), height(r.right));
        }
    }

    public ArrayList<E> searchV2String(E data, Comparator<E> comparator){
        if (root == null) {
            return null;
        } else {
            foundList = new ArrayList<E>();
            searchV2String(root, data, comparator);
            return foundList;
        }
    }

    ArrayList<E> foundList;

    private void searchV2String(Node<E> r, E data, Comparator<E> comparator) {
        if (r == null) {
            return;
        } else {
            if (comparator.compare((E)r.data, (E)data) == 0) {
                foundList.add(r.data);
            }

            searchV2String(r.left, data, comparator);
            searchV2String(r.right, data, comparator);
        }
    }

    public String searchOne(E data, Comparator<E> comparator){
        if (root == null) {
            return null;
        } else {
            return searchOne(root, data, comparator);
        }
    }

    private String searchOne(Node<E> r, E data, Comparator<E> comparator) {
        if (r == null) {
            return "";
        } else {
            if (comparator.compare((E)r.data, (E)data) == 0) {
                return r.data.toString();
            } else {
                return searchOne(r.left, data, comparator) + searchOne(r.right, data, comparator);
            }
        }
    }

    private String inorden(Node<E> r) {
        if (r == null) {
            return "";
        } else {
            return inorden(r.left) + " " + r.data.toString() + " " + inorden(r.right);
        }
    }

    public String showByLevels(){
        String result = "";
        int height = height(root);
        for(int i = 1; i <= height; i++){
            result += showByLevels(root, i);
        }
        return result;
    }
    private String showByLevels(Node<E> r, int level){
        String result = "";
        if(r == null)
            return result;
        if(level == 1)
            result += r.data.toString() + " ";
        else if(level > 1){
            result += showByLevels(r.left, level - 1);
            result += showByLevels(r.right, level - 1);
        }
        return result;
    }
    
    public Node<E> getRoot() {
        return root;
    }

    public void setRoot(Node<E> root) {
        this.root = root;
    }

        public String toString() {
        return inorden(root);
    }


}
