import { NextRequest, NextResponse } from 'next/server'
import pkg from 'pg'
const { Pool } = pkg

const pool = new Pool({
  host:     'localhost',
  port:     5432,
  database: 'orders',
  user:     'demo',
  password: 'demo',
})

export async function GET(_req: NextRequest) {
  console.log('▶️ top‑products using', process.env.DATABASE_URL)
  try {
    const result = await pool.query(`
      SELECT
        date_trunc('minute', created_at) AS minute,
        count(*)                       AS count
      FROM order_placed
      WHERE created_at >= NOW() - INTERVAL '1 hour'
      GROUP BY 1
      ORDER BY 1 DESC
      LIMIT 60
    `)

    const rows = result.rows as { product: string; total_sales: number }[]

    return NextResponse.json(
      rows.map(r => ({
        product: r.product,
        totalSales: Number(r.total_sales),
      }))
    )
  } catch (err: any) {
    console.error('top-products DB error', err)
    return NextResponse.json({ error: err.message }, { status: 500 })
  }
}
