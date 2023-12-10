using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Health : MonoBehaviour
{
    [SerializeField] public int hearts;
    [SerializeField] private List<Image> heartImages;
    [SerializeField] private Sprite emptyHeartImage;
    [SerializeField] private Sprite fullHeartImage;
    

    private int initalHearts;
    // Start is called before the first frame update
    void Start()
    {
        initalHearts = hearts;
    }

    // Update is called once per frame
    void Update()
    {
        if (gameObject.CompareTag("Player"))
        {
            for (int i = 0; i < hearts; i++)
            {
                heartImages[i].sprite = fullHeartImage;
            }
            for (int i = hearts + 1; i < initalHearts; i++)
            {
                heartImages[i].sprite = emptyHeartImage;
            }
        }
        
    }

    public void Damage(int damage)
    {
        hearts -= damage;
        if (hearts <= 0)
        {
            print("game over");
            ResetHearts();
        }
    }

    public void ResetHearts()
    {
        hearts = initalHearts;
    }

    public void AddHeart(int receivedHearts)
    {
        if (hearts + receivedHearts > initalHearts)
            hearts = initalHearts;
        else
            hearts += receivedHearts ;
    }
}
