import { NextRequest, NextResponse } from 'next/server'
import { Pool } from 'pg'

const pool = new Pool({
    connectionString: process.env.DATABASE_URL
  })
  

export async function GET(req: NextRequest) {
  try {
    const { rows } = await pool.query(`
      SELECT
        date_trunc('minute', created_at) AS minute,
        count(*) AS count
      FROM order_placed
      GROUP BY 1
      ORDER BY 1 DESC
      LIMIT 60
    `)
    return NextResponse.json(rows.reverse())
  } catch (err) {
    console.error('API ERROR:', err)
    return NextResponse.json(
      { error: (err as Error).message || 'Unknown error' },
      { status: 500 }
    )
  }
}
