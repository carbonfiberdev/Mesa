package org.example;

import org.example.api.ReadFile;
import org.example.engine.Core;
import org.example.entities.Mesh;
import org.example.entities.Triangle;
import org.example.entities.Vertex;

public class Main {
    public void run(){
        Core.PrintLwjglVersion();
        Core.InitWindow(300,300, "Test");
        Core.InitOpengl();

        String vShdr = ReadFile.Read("vert.shdr");
        String fShdr = ReadFile.Read("frag.shdr");

        Vertex left = new Vertex(-0.5f, -0.5f, 0f);
        Vertex right = new Vertex(0.5f, -0.5f, 0f);
        Vertex up = new Vertex(0f, 0.5f, 0f);

        Mesh mesh = new Mesh(left, right, up);

        Triangle triangle1 = new Triangle(mesh);
        triangle1.SetShaders(vShdr, fShdr);

        Vertex left2 = new Vertex(-0.8f, -0.8f, 0f);
        Vertex right2 = new Vertex(0.3f, -0.2f, 0f);
        Vertex up2 = new Vertex(0f, 0.1f, 0f);

        Mesh mesh2 = new Mesh(left2, right2, up2);

        Triangle triangle2 = new Triangle(mesh2);
        triangle2.SetShaders(vShdr, fShdr);

        Triangle[] triangles = new Triangle[2];
        triangles[0] = triangle1;
        triangles[1] = triangle2;

        Core.Loop(triangles);

        Core.Quit();
    }



    public static void main(String[] args) {
        new Main().run();
    }
}