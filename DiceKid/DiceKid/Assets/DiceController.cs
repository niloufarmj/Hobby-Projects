using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using Random = UnityEngine.Random;


public enum Ability
{
    Fire = 1, 
    Stone = 2,
    Fly = 3
}
public class DiceController : MonoBehaviour
{
    [SerializeField] private GameObject kidPrefab;
    [SerializeField] private GameObject kidsParent;

    [SerializeField] private float speed;
    [SerializeField] private float kidsDistance;
    [SerializeField] private Animator animator;
    [SerializeField] private AnimationClip punchClip;

    [SerializeField] private List<Sprite> diceSideImages;
    [SerializeField] private Image DiceImage;
    [SerializeField] private Sprite FireEnabled;
    [SerializeField] private Sprite StoneEnabled;
    [SerializeField] private Sprite FlyEnabled;
    [SerializeField] private Sprite FlyDisabled;
    [SerializeField] private Sprite FireDisabled;
    [SerializeField] private Sprite StoneDisabled;
    [SerializeField] private Image fireImage;
    [SerializeField] private Image stoneImage;
    [SerializeField] private Image flyImage;
    
    
    [HideInInspector] public delegate void ClickAction();
    [HideInInspector] public event ClickAction onIdle;
    [HideInInspector] public event ClickAction onJumpUp;
    [HideInInspector] public event ClickAction onJumpDown;
    [HideInInspector] public event ClickAction onRun;

    public bool readyToPunch;
    public GameObject enemyToPunch;
    private List<GameObject> kids;
    
    private Rigidbody2D rb;
    public float jumpHeight = 1.0f;
    private int jumpCounter;
    private static readonly int Run = Animator.StringToHash("Run");
    private static readonly int JumpUp = Animator.StringToHash("JumpUp");
    private static readonly int JumpDown = Animator.StringToHash("JumpDown");
    private float playerPrevPositionY;
    private static readonly int Idle = Animator.StringToHash("Idle");
    private static readonly int Punch = Animator.StringToHash("Punch");

    private List<int> triggers;
    private bool isJumping;
    private Dictionary<Ability, int> abilitiesCount;

    private void Awake()
    {
        rb = GetComponent<Rigidbody2D>();
        kids = new List<GameObject>();
        triggers = new List<int>();
        triggers.Add(Run);
        triggers.Add(JumpDown);
        triggers.Add(JumpUp);
        triggers.Add(Idle);
        triggers.Add(Punch);
        abilitiesCount = new Dictionary<Ability, int>();
        abilitiesCount.Add(Ability.Fire, 0);
        abilitiesCount.Add(Ability.Stone, 0);
        abilitiesCount.Add(Ability.Fly, 0);

    }

    private void OnCollisionEnter2D(Collision2D other)
    {
        if (other.gameObject.CompareTag("Ground"))
        {
            jumpCounter = 0;
            isJumping = false;
            SetTrigger(Idle);
        }
    }


    private void OnTriggerEnter2D(Collider2D other)
    {  
        if (other.gameObject.CompareTag("Kid"))
        {
            if (!kids.Contains(other.gameObject))
            {
                if (kids.Count == 0)
                    other.gameObject.transform.position = kidsParent.transform.position;
                else
                {
                    var prevKidPosition = kids[kids.Count - 1].transform.position;
                    if (prevKidPosition.x - transform.position.x > 0)
                    {
                        other.gameObject.transform.position = prevKidPosition + new Vector3(kidsDistance, 0,0);
                    }
                    else
                    {
                        other.gameObject.transform.position = prevKidPosition - new Vector3(kidsDistance, 0,0);
                    }
                }
                kids.Add(other.gameObject);
                other.gameObject.GetComponent<Collider2D>().enabled = false;
            }
        }

        else if (other.gameObject.CompareTag("Heart"))
        {
            Destroy(other.gameObject);
            gameObject.GetComponent<Health>().AddHeart(1);
        }
    }
    
    private void Update()
    {
        foreach (var kid in kids)
        {
            Vector3 target = kidsParent.transform.position;

            var firstKidPos = kids[0].transform.position;
            if (kids.IndexOf(kid) >= 1)
            {
                if (firstKidPos.x - transform.position.x > 0)
                {
                    target = kids[kids.IndexOf(kid) - 1].transform.position + new Vector3(kidsDistance, 0,0);
                }
                else
                {
                    target = kids[kids.IndexOf(kid) - 1].transform.position - new Vector3(kidsDistance, 0,0);
                }
            }
            
            kid.transform.position = Vector3.Lerp(kid.transform.position, target, 0.1f);
        }
        
        MoveLeftRight();
        Jump();
        PunchControl();
        RollDice();
        UpdateAbilities();
        ThrowFire();
        ThrowStone();
        Fly();
        
        playerPrevPositionY = transform.position.y;
        
    }


    private void FixedUpdate()
    {
        
    }

    private IEnumerator push()
    {
        yield return new WaitForSeconds(.2f);
        float forceDirectionX;
        
        if (readyToPunch && enemyToPunch)
        {
            if (transform.position.x < enemyToPunch.transform.position.x)
            {
                forceDirectionX = 20;
            }
            else
            {
                forceDirectionX = -20;
            }     
            enemyToPunch.GetComponent<Rigidbody2D>().AddForce(new Vector2(forceDirectionX,0), ForceMode2D.Impulse);
            Health health = enemyToPunch.GetComponent<Health>();
            health.Damage(1);
            print(health.hearts);
            if (health.hearts == 0)
                Destroy(enemyToPunch);
        }
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
    
    
    private void MoveLeftRight()
    {
        if (Input.GetKey(KeyCode.D))
        {

            transform.rotation = Quaternion.Euler(0, 0, 0);
            foreach (var kid in kids)
            {
                kid.transform.rotation = Quaternion.Euler(0, 0, 0);
            }
            rb.AddForce(new Vector2(speed, 0));
            SetTrigger(Run);

        }

        else if (Input.GetKey(KeyCode.A))
        {
            transform.rotation = Quaternion.Euler(0, 180, 0);
            foreach (var kid in kids)
            {
                kid.transform.rotation = Quaternion.Euler(0, 180, 0);
            }
            rb.AddForce(new Vector2(-speed, 0));
            SetTrigger(Run);

        }

        else
        {

            if (!isJumping)
            {
                SetTrigger(Idle);
            }
            else
            {
                if (playerPrevPositionY - transform.position.y > 0)
                {
                    SetTrigger(JumpDown);

                }
                else
                {
                    SetTrigger(JumpUp);
                }
            }
        }

    }

    private void Jump()
    {
        if (Input.GetKey(KeyCode.W) && jumpCounter < 10)
        {
            isJumping = true;
            jumpCounter++;
            rb.AddForce(new Vector2(0, jumpHeight), ForceMode2D.Impulse);
            SetTrigger(JumpUp);
        }
    }

    private void PunchControl()
    {
        if (Input.GetKeyDown(KeyCode.F))
        {
            SetTrigger(Punch);
            StartCoroutine(push());
        }
    }

    private void RollDice()
    {
        if (Input.GetKeyDown(KeyCode.Space))
        {
             if (kids.Count == 0) return;

            var sacrificedKid = kids[kids.Count - 1];
            kids.Remove(sacrificedKid);
            Destroy(sacrificedKid);

            var randomDice = Random.Range(0, 5);
            DiceImage.sprite = diceSideImages[randomDice];
            // if (randomDice == 0)
            // {
                abilitiesCount[Ability.Fire] += randomDice;
            // }
            // else if (randomDice == 1)
            // {
            //     abilitiesCount[Ability.Stone] += 5;
            // }
            // else if (randomDice == 3)
            // {
            //     abilitiesCount[Ability.Fly] += 100;
            // }

        }
    }

    private void UpdateAbilities()
    {
        if (abilitiesCount[Ability.Fire] > 0)
        {
            fireImage.sprite = FireEnabled;
        }
        else
        {
            fireImage.sprite = FireDisabled;
        }

        if (abilitiesCount[Ability.Stone] > 0)
        {
            stoneImage.sprite = StoneEnabled;
        }
        else
        {
            stoneImage.sprite = StoneDisabled;
        }

        if (abilitiesCount[Ability.Fly] > 0)
        {
            flyImage.sprite = FlyEnabled;
        }
        else
        {
            flyImage.sprite = FlyDisabled;
        }
    }

    private void ThrowFire()
    {
        if (Input.GetKeyDown(KeyCode.Alpha1) && abilitiesCount[Ability.Fire] > 0)
        {        
                print("use fire");
                abilitiesCount[Ability.Fire]--;
            
        }
    }

    private void ThrowStone()
    {
        if (Input.GetKeyDown(KeyCode.Alpha2) && abilitiesCount[Ability.Stone] > 0)
        {
            print("use stone");
            abilitiesCount[Ability.Stone]--;
        }
    }

    private void Fly()
    {
        if (Input.GetKey(KeyCode.Alpha3) && abilitiesCount[Ability.Fly] > 0)
        {
            print("fly");
            isJumping = true;
            abilitiesCount[Ability.Fly]--;
        }
       
    }
}
