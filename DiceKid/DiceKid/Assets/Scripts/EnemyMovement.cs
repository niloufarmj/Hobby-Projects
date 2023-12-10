using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EnemyMovement : MonoBehaviour
{
    private GameObject player;
    [SerializeField] private float range;
    [SerializeField] private int damage;
    [SerializeField] private float speed;
    // Start is called before the first frame update
    private void Start()
    {
        player = GameObject.FindWithTag("Player");
    }

    // Update is called once per frame
    private void Update()
    {
        var dist = player.transform.position.x - transform.position.x;
        if (dist > 0 && dist < range)
        {
            transform.position += new Vector3(speed * Time.deltaTime, 0);
        }
        else if (dist < 0 && dist > -range)
        {
            transform.position -= new Vector3(speed * Time.deltaTime, 0);
        }
    }

    private void OnTriggerEnter2D(Collider2D other)
    {
        if (other.CompareTag("Player"))
        {
            Health health = player.GetComponent<Health>();
            health.Damage(damage);
            Destroy(gameObject);
        }
    }

    private void OnCollisionEnter2D(Collision2D other)
    {
        if (other.gameObject.CompareTag("Player"))
        {
            Health health = player.GetComponent<Health>();
            health.Damage(damage);
            Destroy(gameObject);
        }
    }
}
