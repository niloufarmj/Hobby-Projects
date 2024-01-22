using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PanchitoMove : MonoBehaviour
{

    public Transform pivot;
    public float springRange;
    public float maxVal;

    Rigidbody2D rb;

    // Start is called before the first frame update
    void Start()
    {
        rb = GetComponent<Rigidbody2D>();
        rb.bodyType = RigidbodyType2D.Kinematic;
    }

    bool canDrag = true;
    Vector3 dis;

    private void OnMouseDrag()
    {
        if (!canDrag)
            return;

        var pos = Camera.main.ScreenToWorldPoint(Input.mousePosition);
        dis = pos - pivot.position;
        dis.z = 0;

        if (dis.magnitude > springRange)
        {
            dis = dis.normalized * springRange;
        }
        transform.position = dis + pivot.position;
    }

    private void OnMouseUp()
    {
        if (!canDrag)
            return;
        canDrag = false;

        rb.bodyType = RigidbodyType2D.Dynamic;
        rb.velocity = -dis.normalized * maxVal * dis.magnitude / springRange;
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
