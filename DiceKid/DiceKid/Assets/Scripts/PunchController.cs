using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PunchController : MonoBehaviour
{
    private GameObject Player => GameObject.FindWithTag("Player");
    private DiceController playerScript => Player.GetComponent<DiceController>();
    private void OnTriggerStay2D(Collider2D other)
    {
        playerScript.readyToPunch = true;
        playerScript.enemyToPunch = other.gameObject;
    }

    private void OnTriggerExit2D(Collider2D other)
    {
        playerScript.readyToPunch = false;
        playerScript.enemyToPunch = null;
    }

    private void OnCollisionStay2D(Collision2D other)
    {
        playerScript.readyToPunch = true;
        playerScript.enemyToPunch = other.gameObject;
    }

    private void OnCollisionExit2D(Collision2D other)
    {
        playerScript.readyToPunch = false;
        playerScript.enemyToPunch = null;
    }
}
