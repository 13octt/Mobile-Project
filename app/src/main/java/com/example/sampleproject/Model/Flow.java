package com.example.sampleproject.Model;

import java.util.List;

public class Flow {
    private String id;
    private String type;
    private String name;
    private Position position;
    private Position size;
    private List<Internal> internals;
    private List<Put> outputs;
    private List<Put> inputs;
    private String displayCharacter;

    public static class Put {
        private String id;
        private String name;
        private String type;
        private String nodeId;
        private int index;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getNodeId() {
            return nodeId;
        }

        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        @Override
        public String toString() {
            return "Put{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", type='" + type + '\'' +
                    ", nodeId='" + nodeId + '\'' +
                    ", index=" + index +
                    '}';
        }
    }

    public static class Position {
        private double x;
        private double y;

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public static class Picker{
        String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "Picker{" +
                    "type='" + type + '\'' +
                    '}';
        }
    }

    public static class Internal {
        public static String name;
        public static Picker picker;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Picker getPicker() {
            return picker;
        }

        public void setPicker(Picker picker) {
            this.picker = picker;
        }

        @Override
        public String toString() {
            return "Internal{" +
                    "name='" + name + '\'' +
                    ", picker=" + picker +
                    '}';
        }
    }


    public Flow(String id, String type, String name, Position position, Position size, List<Internal> internals, List<Put> outputs, List<Put> inputs, String displayCharacter) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.position = position;
        this.size = size;
        this.internals = internals;
        this.outputs = outputs;
        this.inputs = inputs;
        this.displayCharacter = displayCharacter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getSize() {
        return size;
    }

    public void setSize(Position size) {
        this.size = size;
    }

    public List<Internal> getInternals() {
        return internals;
    }

    public void setInternals(List<Internal> internals) {
        this.internals = internals;
    }

    public List<Put> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<Put> outputs) {
        this.outputs = outputs;
    }

    public List<Put> getInputs() {
        return inputs;
    }

    public void setInputs(List<Put> inputs) {
        this.inputs = inputs;
    }

    public String getDisplayCharacter() {
        return displayCharacter;
    }

    public void setDisplayCharacter(String displayCharacter) {
        this.displayCharacter = displayCharacter;
    }

    @Override
    public String toString() {
        return "Flow{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", position=" + position +
                ", size=" + size +
                ", internals=" + internals +
                ", outputs=" + outputs +
                ", inputs=" + inputs +
                ", displayCharacter='" + displayCharacter + '\'' +
                '}';
    }
}
