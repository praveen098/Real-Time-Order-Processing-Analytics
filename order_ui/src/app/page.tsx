'use client'

import { useEffect, useState } from 'react'
import axios from 'axios'
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  ResponsiveContainer,
} from 'recharts'

type DataPoint = { minute: string; count: number }

export default function DashboardPage() {
  const [data, setData] = useState<DataPoint[]>([])

  const fetchData = async () => {
    const res = await axios.get<DataPoint[]>('/api/orders-per-minute')
    setData(res.data)
  }

  useEffect(() => {
    fetchData()
    const id = setInterval(fetchData, 10_000)
    return () => clearInterval(id)
  }, [])

  return (
    <main style={{ padding: '1rem' }}>
      <h1>Orders per Minute</h1>
      <ResponsiveContainer width="100%" height={400}>
        <LineChart data={data}>
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="minute" tickFormatter={(t) => t.slice(11, 16)} />
          <YAxis />
          <Tooltip />
          <Line type="monotone" dataKey="count" dot={false} />
        </LineChart>
      </ResponsiveContainer>
    </main>
  )
}
