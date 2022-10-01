package com.example.BackendVolatile.vo.reportVO;

import com.example.BackendVolatile.vo.ResultVO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetSimilarityGraphVO {
    List<Node> nodes;
    List<Link> links;
    List<Category> categories;
    ResultVO response;

    public GetSimilarityGraphVO(List<TempNode> tempNodeList,int n){
        this.nodes = new ArrayList<>();
        this.links = new ArrayList<>();
        this.categories = new ArrayList<>();

        for(int i = 0; i < n; i++){
            categories.add(new Category(tempNodeList.get(i).getValue()));
            nodes.add(new Node(tempNodeList.get(i)));
            links.add(new Link(tempNodeList.get(i)));
        }
        for(int i = n; i < tempNodeList.size(); i++){
            nodes.add(new Node(tempNodeList.get(i)));
            links.add(new Link(tempNodeList.get(i)));
        }
    }

}

@Data
class Node{
    Long name;
    String value;
    Integer symbolSize;
    Long category;
    ToolTip toolTip;



    public Node(TempNode tempNode){
        System.out.println(tempNode.toString());
        this.name = tempNode.getId();
        this.symbolSize =tempNode.getSymbolSize();
        this.category = tempNode.getCategory();
        this.value = tempNode.getValue();
        this.toolTip = new ToolTip();
        this.toolTip.setFormatter(tempNode.getReportId() + "");
    }



}

@Data
class Link{
    Long source;
    Long target;
    Label label;

    public Link(TempNode tempNode){
        this.source = tempNode.getId();
        this.target = tempNode.getTarget();
        this.label = new Label(tempNode.getFormatter() + "%",tempNode.getShow());
    }

}

@Data
@AllArgsConstructor
class Label{
    String formatter;
    Boolean show;
}


@Data
@AllArgsConstructor
class Category{
    String name;
}

@Data
class ToolTip{
    String formatter;
}