using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class KidController : MonoBehaviour
{
    [SerializeField] private Animator animator;
    [HideInInspector] public bool attachedToPlayer;
    private bool eventsSubscribed;
    private GameObject player;
    private static readonly int Idle = Animator.StringToHash("KidIdle");
    private static readonly int Run = Animator.StringToHash("KidRun");
    private static readonly int JumpUp = Animator.StringToHash("KidJumpUp");
    private static readonly int JumpDown = Animator.StringToHash("KidJumpDown");
    private List<int> triggers = new List<int>();
    private DiceController playerScript => player.GetComponent<DiceController>();

    private void Start()
    {
        player = GameObject.FindWithTag("Player");

        triggers.Add(Idle);
        triggers.Add(Run);
        triggers.Add(JumpDown);
        triggers.Add(JumpUp);

        
    }

    private void Update()
    {
        if (!eventsSubscribed)
        {
            if (attachedToPlayer)
            {
                SubscribeEvents();
                eventsSubscribed = true;
            }
        }
    }

    private void SubscribeEvents()
    {
        playerScript.onIdle += OnIdle;
        playerScript.onRun += OnRun;
        playerScript.onJumpDown += OnJumpDown;
        playerScript.onJumpUp += OnJumpUp;
    }

    private void OnIdle()
    {
        SetTrigger(Idle);
    }
    private void OnRun()
    {
        SetTrigger(Run);
    }
    private void OnJumpUp()
    {
        SetTrigger(JumpUp);
    }
    private void OnJumpDown()
    {
        SetTrigger(JumpDown);
    }
    
    
    private void SetTrigger(int triggerToSet)
    {
        foreach (var trigger in triggers)
        {
            if (trigger == triggerToSet)
            {
                animator.SetTrigger(trigger);
            }
            else
            {
                animator.ResetTrigger(trigger);
            }
        }
    }
}
