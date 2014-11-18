class AVLTree <T extends Comparable<T>> {

  private DataNode<T> root;

  private class DataNode <T> {

    protected T data;
    protected DataNode<T> left;
    protected DataNode<T> right;

    DataNode(T data){
      this.data = data;
    }

    protected boolean insert(T data){
      if (this.data == null){
        this.data = data;
      }
      else if (data.compareTo(this.data) < 0){
        if(this.left == null){
          this.left = new DataNode(data);
        } else {
          this.left.insert(data);
        }
      } else if (data.compareTo(this.data) >= 0){
        if(this.right == null){
          this.right = new DataNode(data);
        } else {
          this.right.insert(data);
        }
      }
    }
  }

  public boolean insert(T data){
    root.insert(data);
  }

}
