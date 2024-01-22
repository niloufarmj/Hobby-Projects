using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class PunchitoRestart : MonoBehaviour
{
    public void RestartGame()
    {
        Debug.Log("Helllloooo");
        SceneManager.LoadScene("SampleScene");
    }
}
