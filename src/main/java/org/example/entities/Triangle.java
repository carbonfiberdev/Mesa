package org.example.entities;

import org.example.api.ReadFile;
import org.joml.Matrix3fc;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;

public class Triangle {
    Mesh mesh;
    int VBO;
    int vertex_shader_obj, fragment_shader_obj, shader_program;
    int pointer;
    float size = 1f;
    Vector3f position = new Vector3f(0f,0f,0f);
    public Triangle(){
        Vertex left = new Vertex(-0.5f, -0.5f, 0f);
        Vertex right = new Vertex(0.5f, -0.5f, 0f);
        Vertex up = new Vertex(0f, 0.5f, 0f);

        this.mesh = new Mesh(left, right, up);

        VBO = GL30.glGenBuffers();

        GL30.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBO);
        GL30.glBufferData(GL15.GL_ARRAY_BUFFER, GetMesh(), GL15.GL_STATIC_DRAW);

        GL30.glEnableVertexAttribArray(pointer);
        GL30.glVertexAttribPointer(pointer, 3, GL_FLOAT, false, 0, 0);

        SetShaders(ReadFile.Read("vert.shdr"), ReadFile.Read("frag.shdr"));
    }

    public Triangle(float pos_x, float pos_y){
        Vertex left = new Vertex(-0.5f, -0.5f, 0f);
        Vertex right = new Vertex(0.5f, -0.5f, 0f);
        Vertex up = new Vertex(0f, 0.5f, 0f);

        this.mesh = new Mesh(left, right, up);

        VBO = GL30.glGenBuffers();

        GL30.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBO);
        GL30.glBufferData(GL15.GL_ARRAY_BUFFER, GetMesh(), GL15.GL_STATIC_DRAW);

        GL30.glEnableVertexAttribArray(pointer);
        GL30.glVertexAttribPointer(pointer, 3, GL_FLOAT, false, 0, 0);

        SetShaders(ReadFile.Read("vert.shdr"), ReadFile.Read("frag.shdr"));

        position.x = pos_x;
        position.y = pos_y;

        Matrix4f matrix = new Matrix4f();
        matrix.translate(position);

        int transformLoc = GL20.glGetUniformLocation(shader_program, "transform");
        GL20.glUniformMatrix4fv(transformLoc, false, matrix.get(new float[16]));
    }

    public void Draw(){
        Matrix4f matrix = new Matrix4f();
        matrix.translate(position);

        int transformLoc = GL20.glGetUniformLocation(shader_program, "transform");
        GL20.glUniformMatrix4fv(transformLoc, false, matrix.get(new float[16]));

        GL30.glBufferData(GL15.GL_ARRAY_BUFFER, GetMesh(), GL15.GL_STATIC_DRAW);
        GL30.glUseProgram(shader_program);
        GL30.glDrawArrays(GL_TRIANGLES, 0, 3);
    }
    public void SetShaders(String vertex_shader, String fragment_shader){
        vertex_shader_obj = GL30.glCreateShader(GL20.GL_VERTEX_SHADER);
        GL30.glShaderSource(vertex_shader_obj, vertex_shader);
        GL30.glCompileShader(vertex_shader_obj);

        fragment_shader_obj = GL30.glCreateShader(GL20.GL_FRAGMENT_SHADER);
        GL30.glShaderSource(fragment_shader_obj, fragment_shader);
        GL30.glCompileShader(fragment_shader_obj);

        shader_program = GL30.glCreateProgram();
        GL30.glAttachShader(shader_program, vertex_shader_obj);
        GL30.glAttachShader(shader_program, fragment_shader_obj);

        GL30.glBindAttribLocation(shader_program, pointer, "position");
        GL30.glLinkProgram(shader_program);

        GL30.glDeleteShader(vertex_shader_obj);
        GL30.glDeleteShader(fragment_shader_obj);

        GL30.glUseProgram(shader_program);
    }
    public void SetSize(float size){
        this.size = size;
    }
    public float [] GetMesh(){
        float[] meshArray = new float[9];

        int index = 0;
        meshArray[index++] = mesh.left.x * size;
        meshArray[index++] = mesh.left.y * size;
        meshArray[index++] = mesh.left.z * size;

        meshArray[index++] = mesh.right.x * size;
        meshArray[index++] = mesh.right.y * size;
        meshArray[index++] = mesh.right.z * size;

        meshArray[index++] = mesh.up.x * size;
        meshArray[index++] = mesh.up.y * size;
        meshArray[index] = mesh.up.z * size;

        return meshArray;
    }
}