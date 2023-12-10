using System.Collections.Generic;
using UnityEngine;

public class Maze : MonoBehaviour
{
    public int width;
    public int height;
    public GameObject wallPrefab;
    public float wallLength = 1f;

    private int[,] maze;
    private List<GameObject> walls;

    private void Start()
    {
        GenerateMaze();
        SpawnMaze();
    }

    private void GenerateMaze()
    {
        maze = new int[width, height];
        walls = new List<GameObject>();

        // Initialize maze with walls
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                maze[i, j] = 1;
            }
        }

        // Generate maze using PCG algorithm
        // Add your PCG algorithm code here

        // Example: Randomly remove walls
        for (int i = 1; i < width; i += 2)
        {
            for (int j = 1; j < height; j += 2)
            {
                maze[i, j] = 0;
            }
        }
    }

    private void SpawnMaze()
    {
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                if (maze[i, j] == 1)
                {
                    Vector3 position = new Vector3(i * wallLength, 0f, j * wallLength);
                    GameObject wall = Instantiate(wallPrefab, position, Quaternion.identity);
                    walls.Add(wall);
                }
            }
        }
    }
}